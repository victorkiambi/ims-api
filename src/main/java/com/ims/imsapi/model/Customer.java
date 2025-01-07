package com.ims.imsapi.model;

import com.ims.imsapi.dto.CustomerDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    private String email;
    private String phone;
    private String address;
    private String city;
    private String county;
    private String country;
    private String password;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public CustomerDto toCustomerDto() {
        return new CustomerDto(id, name, email, phone, address, city, county, country);
    }
}
