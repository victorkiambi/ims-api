package com.ims.imsapi.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ims.imsapi.model.Customer;
import com.ims.imsapi.service.CustomerService;
import com.ims.imsapi.utils.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class Oauth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

        // Get the user details from the OAuth2User object
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String picture = oauth2User.getAttribute("picture");

//        // Check if the user is already registered
//        if (!customerService.existsByEmail(email)) {
//            // If not, register the user
//            Customer customer = new Customer();
//            customer.setEmail(email);
//            customer.setName(name);
//            customerService.createCustomer(customer);
//        }

        // Generate a JWT token
        String token = jwtUtil.generateToken(email);

        //Response object
        Map<String, Object> responseObj = new HashMap<>();
        responseObj.put("token", token);
        responseObj.put("user", Map.of("email", email, "name", name, "picture", picture));

        // Write the response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), responseObj);
    }

}
