package com.ims.imsapi.controller;

import com.ims.imsapi.dto.CustomerDto;
import com.ims.imsapi.dto.OrderResponseDto;
import com.ims.imsapi.model.Customer;
import com.ims.imsapi.model.NewCustomerOrder;
import com.ims.imsapi.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        var customerDto = customerService.createCustomer(customer);
        return ResponseEntity.ok(customerDto);
    }

    @PostMapping("/customer/new")
    public ResponseEntity<OrderResponseDto> createNewCustomerOrder(@Valid @RequestBody NewCustomerOrder newCustomerOrder) {
        var orderResponseDto = customerService.createNewCustomerOrder(newCustomerOrder);
        return ResponseEntity.ok(orderResponseDto);
    }

    @GetMapping("/customer/email")
    public ResponseEntity<CustomerDto> getCustomerByEmail(@RequestBody String email) {
        var customer = customerService.getCustomerByEmail(email);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }
}
