package com.product.trial.master.service.impl;

import com.product.trial.master.dto.PanelDTO;
import com.product.trial.master.exception.ExceptionsHandler;
import com.product.trial.master.mapper.PanelMapper;
import com.product.trial.master.model.PanelEntity;
import com.product.trial.master.model.ProductEntity;
import com.product.trial.master.model.UserEntity;
import com.product.trial.master.model.UserProductEntity;
import com.product.trial.master.repository.PanelRepository;
import com.product.trial.master.repository.ProductRepository;
import com.product.trial.master.repository.UserRepository;
import com.product.trial.master.service.PanelService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PanelServiceImpl implements PanelService {


    public static final String USER_NOT_FOUND = "user not found";
    private final UserRepository userRepository;

    private final PanelRepository panelRepository;

    private final ProductRepository productRepository;

    private final PanelMapper panelMapper;

    public PanelServiceImpl(UserRepository userRepository, PanelRepository panelRepository, ProductRepository productRepository, PanelMapper panelMapper) {
        this.userRepository = userRepository;
        this.panelRepository = panelRepository;
        this.productRepository = productRepository;
        this.panelMapper = panelMapper;
    }

    @Override
    public PanelDTO panel(String userId) throws ExceptionsHandler {
        UserEntity userEntity = userRepository.findByUsername(userId).orElseThrow(()->new ExceptionsHandler(USER_NOT_FOUND));
        Optional<PanelEntity> panel = panelRepository.findByUser(userEntity);
        if (panel.isPresent()) {
            return panelMapper.panelToPanelDTO(panel.get());
        }
        throw new ExceptionsHandler("No panel exist for user,pls create one");
    }

    @Override
    public PanelDTO addToPanel(Integer productId, Integer number, String user) throws ExceptionsHandler {

        UserEntity userEntity = userRepository.findByUsername(user).orElseThrow(()->new ExceptionsHandler(USER_NOT_FOUND));
        PanelEntity panel = panelRepository.findByUser(userEntity)
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    PanelEntity newPanel = new PanelEntity();
                    newPanel.setUser(userEntity);
                    return panelRepository.save(newPanel);
                });
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new ExceptionsHandler("Product not found with ID: " + productId));

        Optional<UserProductEntity> existing = panel.getUserProducts()
                .stream()
                .filter(up -> up.getProduct().getProductId().equals(productId))
                .findFirst();

        if (existing.isPresent()) {
            UserProductEntity userProduct = existing.get();
            userProduct.setProductQuantityRequested(userProduct.getProductQuantityRequested() + number);
        } else {
            UserProductEntity newUserProduct = new UserProductEntity();
            newUserProduct.setUserEntity(userEntity);
            newUserProduct.setProduct(product);
            newUserProduct.setProductQuantityRequested(number);
            newUserProduct.setPanel(panel);
            panel.getUserProducts().add(newUserProduct);
        }

        return panelMapper.panelToPanelDTO(panelRepository.save(panel));
    }

    @Override
    public PanelDTO removePanel(Integer productId, Integer number, String user) throws ExceptionsHandler {
        UserEntity userEntity = userRepository.findByUsername(user).orElseThrow(()->new ExceptionsHandler(USER_NOT_FOUND));
        PanelEntity panel = panelRepository.findByUser(userEntity)
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    PanelEntity newPanel = new PanelEntity();
                    newPanel.setUser(userEntity);
                    return panelRepository.save(newPanel);
                });
        productRepository.findById(productId)
                .orElseThrow(() -> new ExceptionsHandler("Product not found with ID: " + productId));

        Optional<UserProductEntity> existing = panel.getUserProducts()
                .stream()
                .filter(up -> up.getProduct().getProductId().equals(productId))
                .findFirst();

        if (existing.isPresent()) {
            UserProductEntity userProduct = existing.get();
            if (userProduct.getProductQuantityRequested() >= number) {
                userProduct.setProductQuantityRequested(userProduct.getProductQuantityRequested() - number);
            } else {
                throw new ExceptionsHandler("you have just "+userProduct.getProductQuantityRequested()+" of product");
            }
        } else {
            throw new ExceptionsHandler("you don't have this product in you panel");

        }

        return panelMapper.panelToPanelDTO(panelRepository.save(panel));
    }

    @Override
    public void dropPanel(String user) throws ExceptionsHandler {
        UserEntity userEntity = userRepository.findByUsername(user).orElseThrow(() -> new ExceptionsHandler(USER_NOT_FOUND));
        PanelEntity panelEntity = panelRepository.findByUser(userEntity).orElseThrow(()-> new ExceptionsHandler("User don't have panel"));
        panelRepository.delete(panelEntity);
    }

}
