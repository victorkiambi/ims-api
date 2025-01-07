package com.ims.imsapi.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private Long id;
    private String name;
    private String payment_method;
    private String shipping_address;
    private String phone;
    private Date date;
    private String token;

}
