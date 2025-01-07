package com.ims.imsapi.controller;

import com.ims.imsapi.model.Customer;
import com.ims.imsapi.service.CustomerService;
import com.ims.imsapi.utils.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomerService customerService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomerService customerService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.customerService = customerService;
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        Customer customer = customerService.loadCustomerbyUsername(authRequest.getEmail());
        return jwtUtil.generateToken(customer.getEmail());
    }
}


@Setter
@Getter
class AuthRequest {
    private String email;
    private String password;

}
