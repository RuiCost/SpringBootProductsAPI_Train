package com.example.produtoisapi.invoiceManagement.api;


import com.example.produtoisapi.exceptions.NotFoundException;
import com.example.produtoisapi.invoiceManagement.services.InvoiceService;
import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.api.ProductView;
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

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Tag(name="Invoice",description = "Invoice Endpoints")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/invoice")
public class InvoiceCrontroller {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceCrontroller.class);


    private final InvoiceService service;

    private final InvoiceViewMapper invoiceViewMapper;

    @Autowired
    private Utils utils;

    @PostMapping
    public ResponseEntity<InvoiceView> create(@Valid @RequestBody final CreateInvoiceRequest resource) {

        final var invoice = service.create(resource);


        return ResponseEntity.ok().body(invoiceViewMapper.toInvoiceView(invoice));
    }


    @GetMapping()
    public Iterable<InvoiceView> findAll(@RequestParam("page") int page, @RequestParam("size") Integer size) {

        return invoiceViewMapper.toInvoiceView(service.findAll(page, size));
    }


    @PatchMapping(value="/{id}/paid")
    public ResponseEntity<InvoiceView> changeStateOfInvoicetoPaid (@PathVariable("id") final Long id){


        final var invoice = service.changeStateOfInvoicetoPaid(id);


        return ResponseEntity.ok().body(invoiceViewMapper.toInvoiceView(invoice));
    }

    @PatchMapping(value="/{id}/cancel")
    public ResponseEntity<InvoiceView> changeStateOfInvoicetoCANCEL (@PathVariable("id") final Long id){


        final var invoice = service.changeStateOfInvoicetoCANCEL(id);


        return ResponseEntity.ok().body(invoiceViewMapper.toInvoiceView(invoice));
    }

    @PatchMapping(value="/{id}/pending")
    public ResponseEntity<InvoiceView> changeStateOfInvoicetoPENDING (@PathVariable("id") final Long id){


        final var invoice = service.changeStateOfInvoicetoPENDING(id);


        return ResponseEntity.ok().body(invoiceViewMapper.toInvoiceView(invoice));
    }


}
