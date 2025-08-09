package com.example.produtoisapi.ProductInvoiceManagement.api;


import com.example.produtoisapi.ProductInvoiceManagement.services.ProductInvoiceService;
import com.example.produtoisapi.ShopingCartManagement.services.ShopingCartService;
import com.example.produtoisapi.invoiceManagement.api.CreateInvoiceRequest;
import com.example.produtoisapi.invoiceManagement.services.InvoiceService;
import com.example.produtoisapi.utils.Utils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Tag(name="Invoice",description = "Invoice Endpoints")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productInvoice")
public class ProductInvoiceCrontroller {

    private static final Logger logger = LoggerFactory.getLogger(ProductInvoiceCrontroller.class);

    private final ShopingCartService shopingCartService;

    private final ProductInvoiceService service;

    private final ProductInvoiceViewMapper productInvoiceViewMapper;

    @Autowired
    private Utils utils;

    @PostMapping
    public ResponseEntity<ProductInvoiceView> create(HttpServletRequest request, @Valid @RequestBody final CreateProductInvoiceRequest resource) {

        Long userId = utils.getUserByToken(request);

        shopingCartService.deleteCartByUserId(userId);

        final var producrInvoice = service.create(resource);


        return ResponseEntity.ok().body(productInvoiceViewMapper.toProductInvoiceView(producrInvoice));
    }



/*
    @GetMapping()
    public Iterable<ProductInvoiceView> findAll(@RequestParam("page") int page, @RequestParam("size") Integer size) {

        return productInvoiceViewMapper.toInvoiceView(service.findAll(page, size));
    }


    @PatchMapping(value="/{id}/paid")
    public ResponseEntity<ProductInvoiceView> changeStateOfInvoicetoPaid (@PathVariable("id") final Long id){


        final var invoice = service.changeStateOfInvoicetoPaid(id);


        return ResponseEntity.ok().body(productInvoiceViewMapper.toInvoiceView(invoice));
    }

    @PatchMapping(value="/{id}/cancel")
    public ResponseEntity<ProductInvoiceView> changeStateOfInvoicetoCANCEL (@PathVariable("id") final Long id){


        final var invoice = service.changeStateOfInvoicetoCANCEL(id);


        return ResponseEntity.ok().body(productInvoiceViewMapper.toInvoiceView(invoice));
    }

    @PatchMapping(value="/{id}/pending")
    public ResponseEntity<ProductInvoiceView> changeStateOfInvoicetoPENDING (@PathVariable("id") final Long id){


        final var invoice = service.changeStateOfInvoicetoPENDING(id);


        return ResponseEntity.ok().body(productInvoiceViewMapper.toInvoiceView(invoice));
    }


 */

}
