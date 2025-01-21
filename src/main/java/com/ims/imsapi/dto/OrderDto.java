package com.ims.imsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long customer_id;
    private Date created_at;
    private String status;
    private Float total;

    private String payment_method;
    private String payment_status;
    private String shipping_method;
    private String shipping_status;
    private String notes;

}

