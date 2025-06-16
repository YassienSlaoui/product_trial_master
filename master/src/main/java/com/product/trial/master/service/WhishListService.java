package com.product.trial.master.service;


import com.product.trial.master.dto.ProductDTO;
import com.product.trial.master.exception.ExceptionsHandler;

import java.util.List;

public interface WhishListService {

    List<ProductDTO> whishList(String username) throws ExceptionsHandler;
    List<ProductDTO> addProductToWhishList(String username,Integer productId) throws ExceptionsHandler;
    List<ProductDTO> removeProductFromWhishList(String username,Integer productId) throws ExceptionsHandler;
    void dropWhishList(String username) throws ExceptionsHandler;
}
