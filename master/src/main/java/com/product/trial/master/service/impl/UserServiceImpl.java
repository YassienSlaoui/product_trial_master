package com.product.trial.master.service.impl;


import com.product.trial.master.config.JwtUtil;
import com.product.trial.master.dto.AuthRequest;
import com.product.trial.master.dto.UserDTO;
import com.product.trial.master.exception.ExceptionsHandler;
import com.product.trial.master.mapper.UserMapper;
import com.product.trial.master.model.UserEntity;
import com.product.trial.master.repository.UserRepository;
import com.product.trial.master.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserDetailsService userDetailsService;
    @Autowired
    public UserServiceImpl(UserRepository userRepository,UserMapper userMapper
            ,PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil,UserDetailsService userDetailsService){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void registerUser(@Validated UserDTO userDTO) throws ExceptionsHandler {

        UserEntity user = userMapper.userDTOToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Optional<UserEntity> userEntity = userRepository.findByEmail(userDTO.getEmail());
        if(userEntity.isPresent()) throw new ExceptionsHandler("User with email "+ userDTO.getEmail()+" already exist");
        userRepository.save(user);
    }

    @Override
    public String createAuthenticationToken(AuthRequest authRequest) {
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
       );
        final UserEntity userDetails = (UserEntity) userDetailsService.loadUserByUsername(authRequest.getEmail());
        return jwtUtil.generateToken(userDetails);
    }


}
