package com.product.trial.master.service;

import com.product.trial.master.dto.AuthRequest;
import com.product.trial.master.dto.UserDTO;
import com.product.trial.master.exception.ExceptionsHandler;

public interface UserService {

    void registerUser(UserDTO userDTO) throws ExceptionsHandler;
    String createAuthenticationToken (AuthRequest authRequest);
}
