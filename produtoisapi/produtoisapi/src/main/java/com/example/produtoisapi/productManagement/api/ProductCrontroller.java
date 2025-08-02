package com.example.produtoisapi.productManagement.api;


import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.productManagement.services.ProductService;
import com.example.produtoisapi.userManagement.api.UploadFileResponse;
import com.example.produtoisapi.userManagement.model.Role;
import com.example.produtoisapi.userManagement.model.User;
import com.example.produtoisapi.userManagement.repositories.UserRepository;
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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.produtoisapi.exceptions.NotFoundException;

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

    private final UserRepository userRepository;

    @Autowired
    private final Utils utils;

    //CRUD


    //Create
    @Operation(summary = " Create a Product")
    @RolesAllowed({Role.ADMIN})
    @PostMapping
    public ResponseEntity<ProductView> create(@Valid @RequestBody final CreateProductRequest resource) {

        final var product = service.create(resource);



        // for version control:
        final var newDeviceUri = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(product.getId().toString())
                .build().toUri();

        return ResponseEntity.created(newDeviceUri).eTag(Long.toString(product.getVersion()))
                .body(productViewMapper.toProductView(product));
    }

    //Request
    @Operation(summary = "Gets a specific product by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductView> findById(
            @PathVariable("id") @Parameter(description = "The id of the product to find") final String id) {
        final var plan = service.findProductByID(id).orElseThrow(() -> new NotFoundException(Product.class, id));

        return ResponseEntity.ok().eTag(Long.toString(plan.getVersion())).body(productViewMapper.toProductView(plan));
    }

    @Operation(summary = "Gets a specific product by id")
    @GetMapping(value = "/name/{name}")
    public ResponseEntity<ProductView> findByName(
            @PathVariable("name") @Parameter(description = "The id of the plan to find") final String name) {

        final var plan = service.findProductByName(name).orElseThrow(() -> new NotFoundException(Product.class, name));
        return ResponseEntity.ok().eTag(Long.toString(plan.getVersion())).body(productViewMapper.toProductView(plan));
    }


    @Operation(summary = "Gets a specific product by id")
    @GetMapping()
    public Iterable<ProductView> findAll(@RequestParam("page") int page, @RequestParam("size") Integer size) {

        // For now we wont worry about specifics to what users and admins can see
        // For example we may have expired products but we dont need to show this to clients, only admins shoud see
        // we can do this by adding : findAll(HttpServletRequest request, ...)
        // And then something like:
        /*

                long id = utils.getUserByToken(request);

        User user = userRepository.getById(id);

        Set<Role> userRoles = user.getAuthorities();

        Iterator<Role> iterator = userRoles.iterator();

        Role firstRole = iterator.next();

        String roleString = firstRole.toString();

        String roleCustomer = "Role(authority=CUSTOMER)";
        String roleAdmin = "Role(authority=ADMIN)";

        if(roleString.equals(roleCustomer) || roleString.equals(roleSub))
        {
            return planViewMapper.toProductView(service.findAll_User(page, size));
        }
        else {
            return planViewMapper.toProductView(service.findAll(page, size));
        }
         */

        return productViewMapper.toProductView(service.findAll(page, size));
    }


    //Update


    @Operation(summary = "Change description or price of product")
    @RolesAllowed({Role.ADMIN})
    @PatchMapping(value = "/{id}")
    public ResponseEntity<ProductView> partialUpdate(HttpServletRequest request,
                                                  @PathVariable("id") @Parameter(description = "The planType of the plan to update") final String idProduct,
                                                  @Valid @RequestBody final EditProductRequest resource) {

        final String ifMatchValue = request.getHeader("If-Match");
        if (ifMatchValue == null || ifMatchValue.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "You must issue a conditional PATCH using 'if-match'");
        }
        Long id1 = utils.getUserByToken(request);
        User user = userRepository.getById(id1);
        final var plan = service.partialUpdate(idProduct, resource, getVersionFromIfMatchHeader(ifMatchValue),user);
        return ResponseEntity.ok().eTag(Long.toString(plan.getVersion())).body(productViewMapper.toProductView(plan));
    }


    //Delete

    private Long getVersionFromIfMatchHeader(final String ifMatchHeader) {
        if (ifMatchHeader.startsWith("\"")) {
            return Long.parseLong(ifMatchHeader.substring(1, ifMatchHeader.length() - 1));
        }
        return Long.parseLong(ifMatchHeader);
    }
    @Operation(summary = "Deletes an existing plan")
    @RolesAllowed({Role.ADMIN})
    @DeleteMapping(value = "{id}")
    public ResponseEntity<ProductView> delete(final WebRequest request,
                                           @PathVariable("id") @Parameter(description = "The planType of the plan to delete") final String id) {
        final String ifMatchValue = request.getHeader("If-Match");
        if (ifMatchValue == null || ifMatchValue.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "You must issue a conditional DELETE using 'if-match'");
        }
        final int count = service.deleteById(id, getVersionFromIfMatchHeader(ifMatchValue));

        return count == 1 ? ResponseEntity.noContent().build() : ResponseEntity.status(409).build();
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
