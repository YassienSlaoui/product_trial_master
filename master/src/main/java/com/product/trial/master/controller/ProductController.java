package com.product.trial.master.controller;

import com.product.trial.master.dto.ProductDTO;
import com.product.trial.master.exception.ExceptionsHandler;
import com.product.trial.master.model.UserEntity;
import com.product.trial.master.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    public static final String MAIL = "admin@admin.com";
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable("id") int id) throws ExceptionsHandler {
        return productService.getProduct(id);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO, Authentication authentication) throws ExceptionsHandler {
        UserEntity userEntity = ((UserEntity) authentication.getPrincipal());
        if (MAIL.equals(userEntity.getEmail())) {
            return  ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDTO));
        } else {
            throw new ExceptionsHandler("you are not allowed to do this operation",HttpStatus.FORBIDDEN);
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") int id, @RequestBody ProductDTO productDTO, Authentication authentication) throws ExceptionsHandler {
        UserEntity userEntity = ((UserEntity) authentication.getPrincipal());
        if (MAIL.equals(userEntity.getEmail())) {
            return  ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, productDTO));
        } else {
            throw new ExceptionsHandler("you are not allowed to do this operation",HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") int id, Authentication authentication) throws ExceptionsHandler {
        UserEntity userEntity = ((UserEntity) authentication.getPrincipal());
        if (MAIL.equals(userEntity.getEmail())) {
            productService.deleteProduct(id);
        } else {
            throw new ExceptionsHandler("you are not allowed to do this operation",HttpStatus.FORBIDDEN);
        }

    }
}