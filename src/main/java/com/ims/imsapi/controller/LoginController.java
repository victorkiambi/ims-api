package com.ims.imsapi.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LoginController {

    @GetMapping("/login/google")
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/v1/oauth2/authorize/google");
    }
}
