package com.ims.imsapi.service;

import com.ims.imsapi.dto.OrderDto;
import com.ims.imsapi.model.Order;
import com.ims.imsapi.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto createOrder(Order order) {
        Order newOrder = orderRepository.save(order);
        return newOrder.toOrderDto();
    }

    public List<OrderDto> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findOrdersByCustomer_id(customerId)
                .stream()
                .map(Order::toOrderDto)
                .collect(Collectors.toList());
    }

    public OrderDto getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new RuntimeException("Order not found");
        }
        return order.get().toOrderDto();
    }

}
