package com.ims.imsapi.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class NewCustomerOrder {
    private Customer customer;
    private Order order;
    private List<OrderItem> orderItem;
}
