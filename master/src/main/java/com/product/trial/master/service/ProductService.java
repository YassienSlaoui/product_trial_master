package com.product.trial.master.service;

import com.product.trial.master.dto.ProductDTO;
import com.product.trial.master.exception.ExceptionsHandler;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getProducts();
    ProductDTO createProduct(ProductDTO product) throws ExceptionsHandler;

    ProductDTO updateProduct(Integer id, ProductDTO product) throws ExceptionsHandler;
    ProductDTO getProduct(Integer id) throws ExceptionsHandler;
    void deleteProduct(Integer id) throws ExceptionsHandler;

}
