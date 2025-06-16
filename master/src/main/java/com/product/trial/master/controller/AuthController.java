package com.product.trial.master.controller;


import com.product.trial.master.dto.AuthRequest;
import com.product.trial.master.dto.UserDTO;
import com.product.trial.master.exception.ExceptionsHandler;
import com.product.trial.master.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/token")
    public String login(@RequestBody AuthRequest authRequest) {
        return userService.createAuthenticationToken(authRequest);
    }

    @PostMapping("/account")
    public ResponseEntity<Void> register(@Valid @RequestBody UserDTO user) throws ExceptionsHandler {
        userService.registerUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
