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
import org.springframework.data.domain.Page;
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
import java.nio.file.Paths;
import java.util.Optional;

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

    @Operation(summary = "Searches for products by query and optional category with pagination")
    @GetMapping("/search")
    public ResponseEntity<Iterable<ProductView>> searchProducts(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        final var resultPage = service.searchProducts(query, category, page, size);
        return ResponseEntity.ok(productViewMapper.toProductView(resultPage));
    }

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
    @RolesAllowed({Role.ADMIN, Role.CUSTOMER})
    @PostMapping("/photo/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UploadFileResponse> uploadFile(
            HttpServletRequest request, // <-- Add this line
            @RequestParam("file") final MultipartFile file,
            @PathVariable("id") @Parameter(description = "ID of product that we want to give the photo") final String id
    ) throws URISyntaxException {

        // Faz o upload do ficheiro
        final UploadFileResponse up = doUploadFile(file.getOriginalFilename(), file);

        // Usa o service layer para obter o produto
        final Product product = service.findProductByID(id)
                .orElseThrow(() -> new NotFoundException(Product.class, id));

        // Create edit request
        EditProductRequest edit = new EditProductRequest();
        edit.setImageURL(up.getFileDownloadUri()); // assuming `photo` is a field

        Long userId = utils.getUserByToken(request);
        User user = userRepository.getById(userId);

        // Call your update logic (you may want to extract shared logic for reuse)
        service.partialUpdate(product.getId(), edit, product.getVersion(), user);

        return ResponseEntity.created(new URI(up.getFileDownloadUri())).body(up);
    };

    @Operation(summary = "Download a photo of a product by product ID")
    @RolesAllowed({Role.ADMIN, Role.CUSTOMER})
    @GetMapping("/photo/product/{id}")
    public ResponseEntity<Resource> downloadProductImage(@PathVariable("id") String id, HttpServletRequest request) {
        // Find product by ID
        Product product = service.findProductByID(id)
                .orElseThrow(() -> new NotFoundException(Product.class, id));

        // Get the image URL or filename saved in product
        String imageUrl = product.getImageURL();
        if (imageUrl == null || imageUrl.isEmpty()) {
            // Return 404 or an empty black image resource, or null response
            return ResponseEntity.notFound().build();
        }

        // Load the file as a Resource using your file storage service
        Resource resource = fileStorageService.loadFileAsResource(extractFilenameFromURL(imageUrl));

        // Try to determine file content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            // Default to binary stream if type can't be determined
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // Helper method to extract filename from URL
    private String extractFilenameFromURL(String url) {
        // Assuming your URLs are like http://server/path/filename.ext
        return Paths.get(URI.create(url).getPath()).getFileName().toString();
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
