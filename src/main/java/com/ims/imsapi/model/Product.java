package com.ims.imsapi.model;

import com.ims.imsapi.dto.ProductDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity()
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    private String image_url;

    @Column(insertable=false, updatable=false)
    private Long category_id;
    private Long code;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItem;


    public ProductDto toProductDto() {
        return new ProductDto(id, name, description, price, quantity, image_url, category_id, code);
    }
}
