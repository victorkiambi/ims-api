package com.ims.imsapi.model;

import com.ims.imsapi.dto.OrderDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(insertable=false, updatable=false)
    private Long customer_id;
    private String status;
    @NotBlank(message = "Payment method is required")
    private String payment_method;
    @NotBlank(message = "Payment status is required")
    private String payment_status;
    @NotBlank(message = "Shipping address is required")
    private String shipping_address;
    @NotBlank(message = "Shipping city is required")
    private String shipping_city;

    private String shipping_method;
    @NotBlank(message = "Shipping country is required")
    private String shipping_country;
    @NotBlank(message = "Shipping county is required")
    private String shipping_county;
    private Date created_at;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public OrderDto toOrderDto() {
        return new OrderDto(id, customer_id, status, payment_method, payment_status, shipping_address, shipping_city, shipping_method, shipping_country, shipping_county);
    }

}
