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
    private int stock;
    private String sku;
    private String image_url;
    private String type;

    @Column(insertable=false, updatable=false)
    private Long category_id;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItem;


    public ProductDto toProductDto() {
        return new ProductDto(id, name, description, price, quantity, image_url, quantity, description, category_id, type);
    }
}
