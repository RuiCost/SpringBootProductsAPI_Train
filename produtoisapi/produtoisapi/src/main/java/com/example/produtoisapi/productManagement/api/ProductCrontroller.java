package com.example.produtoisapi.productManagement.api;


import com.example.produtoisapi.productManagement.services.ProductService;
import com.example.produtoisapi.userManagement.api.UploadFileResponse;
import com.example.produtoisapi.userManagement.model.Role;
import com.example.produtoisapi.userManagement.model.User;
import com.example.produtoisapi.userManagement.services.FileStorageService;
import com.example.produtoisapi.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Tag(name="Products",description = "Products Endpoints")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductCrontroller {

    private static final Logger logger = LoggerFactory.getLogger(ProductCrontroller.class);

    // where our business logic stays before calling repo
    private final ProductService service;

    private final ProductViewMapper productViewMapper;

    private final FileStorageService fileStorageService;


    @Autowired
    private final Utils utils;


    @Operation(summary = " Create a Product")
    @RolesAllowed({Role.ADMIN})
    @PostMapping
    public ResponseEntity<ProductView> create(@Valid @RequestBody final CreateProductRequest resource) {

        final var product = service.create(resource);


        // for version control:
        final var newDeviceUri = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(product.getId().toString())
                .build().toUri();

        return ResponseEntity.created(newDeviceUri).eTag(Long.toString(product.getVersion()))
                .body(productViewMapper.toPlanView(product));
    }

    public UploadFileResponse doUploadFile(final String username, final MultipartFile file) {

        final String fileName = fileStorageService.storeFile(username, file);

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath() // <- pega só o domínio + contexto (ex: http://localhost:8080)
                .path("/api/product/photo/")
                .path(fileName)
                .toUriString();

        fileDownloadUri = fileDownloadUri.replace("/photos/", "/photo/");

        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }



    @Operation(summary = "Uploads a photo of a product")
    @RolesAllowed({Role.ADMIN, Role.CUSTOMER, })
    @PostMapping("/photo/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") final MultipartFile file,
        @PathVariable("name") @Parameter(description = "name of product that we want to give the foto") final String name) throws URISyntaxException {
        System.out.println(name);
        final UploadFileResponse up = doUploadFile(name, file);
        return ResponseEntity.created(new URI(up.getFileDownloadUri())).body(up);

    }

    @Operation(summary = "Downloads a photo of a user")
    @RolesAllowed({ Role.ADMIN, Role.CUSTOMER})
    @GetMapping("/photo/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable final String fileName,
                                                 final HttpServletRequest request) {
        final Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (final IOException ex) {
            logger.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
