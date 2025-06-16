package com.product.trial.master.service.impl;


import com.product.trial.master.dto.ProductDTO;
import com.product.trial.master.exception.ExceptionsHandler;
import com.product.trial.master.mapper.ProductMapper;
import com.product.trial.master.model.ProductEntity;
import com.product.trial.master.repository.ProductRepository;
import com.product.trial.master.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {



    private final ProductMapper productMapper;

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getProducts() {
        return productMapper.productListToProductDTOList(productRepository.findAll());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) throws ExceptionsHandler {
        Optional<ProductEntity> productEntity = productRepository.findById(productDTO.getId());
        if(productEntity.isPresent()){
            throw new ExceptionsHandler("Product with id "+productDTO.getId()+" is already exist");
        }
        productDTO.setCreatedAt((new Date()).getTime());
        return productMapper.productEntityToProductDTO(productRepository.save(productMapper.productDTOtoProduct(productDTO)));
    }

    @Override
    public ProductDTO updateProduct(Integer id, ProductDTO productDTO) throws ExceptionsHandler {
        ProductEntity prodUpdated = productRepository.findById(id).orElseThrow(()->  new ExceptionsHandler("Product  with id : "+id+" is not exist"));
        prodUpdated.setCode(productDTO.getCode());
        prodUpdated.setName(productDTO.getName());
        prodUpdated.setDescription(productDTO.getDescription());
        prodUpdated.setImage(productDTO.getImage());
        prodUpdated.setCategory(productDTO.getCategory());
        prodUpdated.setPrice(productDTO.getPrice());
        prodUpdated.setQuantity(productDTO.getQuantity());
        prodUpdated.setInternalReference(productDTO.getInternalReference());
        prodUpdated.setShellId(productDTO.getShellId());
        prodUpdated.setInventoryStatus(productDTO.getInventoryStatus());
        prodUpdated.setRating(productDTO.getRating());
        prodUpdated.setUpdatedAt((new Date()).getTime());
        return productMapper.productEntityToProductDTO(productRepository.save(prodUpdated));
    }

    @Override
    public ProductDTO getProduct(Integer id) throws ExceptionsHandler {
        ProductEntity prod = productRepository.findById(id).orElseThrow(()->  new ExceptionsHandler("Product  with id: "+id+" is not exist"));

        return productMapper.productEntityToProductDTO(prod);
    }

    @Override
    public void deleteProduct(Integer id) throws ExceptionsHandler {
        ProductEntity prod = productRepository.findById(id).orElseThrow(()->  new ExceptionsHandler("Product  with id: "+id+" is not exist"));

        productRepository.delete(prod);
    }

}