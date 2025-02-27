package com.ims.imsapi.service;

import com.ims.imsapi.dto.CustomerDto;
import com.ims.imsapi.dto.OrderResponseDto;
import com.ims.imsapi.model.Customer;
import com.ims.imsapi.model.NewCustomerOrder;
import com.ims.imsapi.model.Order;
import com.ims.imsapi.model.OrderItem;
import com.ims.imsapi.repository.CustomerRepository;
import com.ims.imsapi.repository.OrderRepository;

import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    public Customer loadCustomerbyUsername(String email) throws UsernameNotFoundException {
        var customer = customerRepository.findByEmail(email);
        if (customer != null) {
            return Customer.builder()
                    .email(customer.getEmail())
                    .password(customer.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public OrderResponseDto createNewCustomerOrder(NewCustomerOrder newCustomerOrder) {

        var newCustomer = Customer.builder()
                .email(newCustomerOrder.getCustomer().getEmail())
                .name(newCustomerOrder.getCustomer().getName())
                .phone(newCustomerOrder.getCustomer().getPhone())
                .password(newCustomerOrder.getCustomer().getPassword())
                .build();


        var customer = customerRepository.save(newCustomer);
        var token = "";

        var newOrder = Order.builder()
                .customer_id(customer.getId())
                .status(newCustomerOrder.getOrder().getStatus())
                .payment_method(newCustomerOrder.getOrder().getPayment_method())
                .payment_status(newCustomerOrder.getOrder().getPayment_status())
                .shipping_method(newCustomerOrder.getOrder().getShipping_method())

                .build();

        var newOrderSaved = orderRepository.save(newOrder);

        var newOrderItems = newCustomerOrder.getOrderItem().stream().map(orderItem ->
                OrderItem.builder()
                        .order_id(newOrderSaved.getId())
                        .product_id(orderItem.getProduct_id())
                        .quantity(orderItem.getQuantity())
                        .price(orderItem.getPrice())
                        .total(orderItem.getTotal())
                        .build()).toList();

        newOrderItems.forEach(orderItem -> orderItem.setOrder(newOrderSaved));

        return OrderResponseDto.builder()
                .date(newOrderSaved.getCreated_at())
                .id(newOrderSaved.getId())
                .name(customer.getName())
                .payment_method(newOrderSaved.getPayment_method())
                .phone(customer.getPhone())
                .token(token)
                .build();

    }

    public CustomerDto getCustomerByEmail(String email) {
        var customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        return customer.toCustomerDto();
    }

    public CustomerDto getCustomerById(long id) {
        var customer = customerRepository.findById(id);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        return customer.toCustomerDto();
    }

}
