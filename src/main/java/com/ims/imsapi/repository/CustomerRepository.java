package com.ims.imsapi.repository;

import com.ims.imsapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);
    Customer findById(long id);
    boolean existsByEmail(String email);

}
