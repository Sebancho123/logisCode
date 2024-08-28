package com.logistica.logisCode.controller;

import com.logistica.logisCode.dto.AuthLoginRequestDTO;
import com.logistica.logisCode.dto.AuthResponseDTO;
import com.logistica.logisCode.service.UserDetailsServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {

        return new ResponseEntity<>(this.userDetailsServiceImp.loginUser(userRequest), HttpStatus.OK);

    }

}
