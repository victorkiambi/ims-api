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
    private Float total;
    private String payment_method;
    private String payment_status;
    private String shipping_method;
    private String shipping_status;
    private String notes;
    private Date created_at;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public OrderDto toOrderDto() {
        return new OrderDto(id, customer_id, created_at, status, total, payment_method, payment_status, shipping_method, shipping_status, notes);
    }

}
