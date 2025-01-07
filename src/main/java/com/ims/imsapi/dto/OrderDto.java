package com.ims.imsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long customer_id;
    private String created_at;
    private String status;
    private String shipping_address;
    private String shipping_city;
    private String shipping_county;
    private String shipping_country;
    private String payment_method;
    private String payment_status;
}

