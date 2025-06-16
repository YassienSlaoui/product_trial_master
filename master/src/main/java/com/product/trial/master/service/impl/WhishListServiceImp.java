package com.product.trial.master.service.impl;

import com.product.trial.master.dto.ProductDTO;
import com.product.trial.master.exception.ExceptionsHandler;
import com.product.trial.master.mapper.ProductMapper;
import com.product.trial.master.model.ProductEntity;
import com.product.trial.master.model.UserEntity;
import com.product.trial.master.model.WhichList;
import com.product.trial.master.repository.ProductRepository;
import com.product.trial.master.repository.UserRepository;
import com.product.trial.master.repository.WhichListRepository;
import com.product.trial.master.service.ProductService;
import com.product.trial.master.service.WhishListService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WhishListServiceImp implements WhishListService {

    private final UserRepository userRepository;
    private final WhichListRepository whichListRepository;
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;


    public WhishListServiceImp(ProductService productService, UserRepository userRepository, WhichListRepository whichListRepository, ProductRepository productRepository, ProductMapper productMapper) {
        this.userRepository = userRepository;
        this.whichListRepository = whichListRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> whishList(String username) throws ExceptionsHandler {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new ExceptionsHandler("user not exist"));
        Optional<WhichList> whichList = whichListRepository.findByUser(userEntity);
        if (whichList.isPresent()) {
            return productMapper.productListToProductDTOList(whichList.get().getProducts());
        }
        throw new ExceptionsHandler("user don't have whichList");
    }

    @Override
    public List<ProductDTO> addProductToWhishList(String username, Integer productId) throws ExceptionsHandler {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new ExceptionsHandler("user not exist"));
        Optional<WhichList> whichList = whichListRepository.findByUser(userEntity);
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new ExceptionsHandler("product not exist"));
        if (whichList.isPresent()) {
            Optional<ProductEntity> product = whichList.get().getProducts().stream().filter(up -> up.getProductId().equals(productId))
                    .findFirst();

            if (product.isPresent()) {
                throw new ExceptionsHandler("product already exist in panel");
            }
            whichList.get().getProducts().add(productEntity);
            return productMapper.productListToProductDTOList(whichListRepository.save(whichList.get()).getProducts());
        }
        WhichList newWhichList = new WhichList();
        newWhichList.setUser(userEntity);
        newWhichList.getProducts().add(productEntity);
        return productMapper.productListToProductDTOList(whichListRepository.save(newWhichList).getProducts());
    }

    @Override
    public List<ProductDTO> removeProductFromWhishList(String username, Integer productId) throws ExceptionsHandler {

        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new ExceptionsHandler("user not exist"));
        Optional<WhichList> whichList = whichListRepository.findByUser(userEntity);
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new ExceptionsHandler("product not exist"));
        if (whichList.isPresent()) {
            Optional<ProductEntity> product = whichList.get().getProducts().stream().filter(up -> up.getProductId().equals(productId))
                    .findFirst();

            if (product.isPresent()) {
                whichList.get().getProducts().remove(productEntity);
                return productMapper.productListToProductDTOList(whichListRepository.save(whichList.get()).getProducts());
            }
            throw new ExceptionsHandler("Product not exist in your which list");
        }
        throw new ExceptionsHandler("You don't have which List");
    }

    @Override
    public void dropWhishList(String username) throws ExceptionsHandler {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new ExceptionsHandler("user not exist"));
        Optional<WhichList> whichList = whichListRepository.findByUser(userEntity);

        if (whichList.isPresent()) {
            whichList.get().setProducts(new ArrayList<>());
            whichListRepository.save(whichList.get());
        } else {
            throw new ExceptionsHandler("You don't have which List");

        }
    }
}
