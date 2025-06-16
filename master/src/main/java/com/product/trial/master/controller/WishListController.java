package com.product.trial.master.controller;


import com.product.trial.master.dto.ProductDTO;
import com.product.trial.master.exception.ExceptionsHandler;
import com.product.trial.master.service.WhishListService;
import jakarta.ws.rs.QueryParam;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/wishList")
public class WishListController {

    private final WhishListService whishListService;

    public WishListController(WhishListService whishListService) {
        this.whishListService = whishListService;
    }

    @GetMapping
    public List<ProductDTO> whishList(Principal principal) throws ExceptionsHandler {
        return whishListService.whishList(principal.getName());
    }
    @PostMapping
    public List<ProductDTO> addToWishList(Principal principal, @QueryParam("productId") Integer productId) throws ExceptionsHandler {
        return whishListService.addProductToWhishList(principal.getName(),productId );
    }

    @DeleteMapping
    public List<ProductDTO> removeFromWishList(Principal principal, @QueryParam("productId") Integer productId) throws ExceptionsHandler {
        return whishListService.removeProductFromWhishList(principal.getName(),productId );
    }
    @DeleteMapping("/drop")
    public void dropWishList(Principal principal) throws ExceptionsHandler {
        whishListService.dropWhishList(principal.getName());
    }
}
