package com.ims.imsapi.service;

import com.ims.imsapi.dto.ProductDto;
import com.ims.imsapi.model.Product;
import com.ims.imsapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*
    * Find all products
     */
    public List<ProductDto> findAll() {
       var products = productRepository.findAll();
       if (products.isEmpty()) {
           return List.of();
       }
       return products.stream()
             .map(Product::toProductDto)
             .collect(Collectors.toList());
    }

    /*
    * Find product by id
     */

    public ProductDto findById(Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        return product.toProductDto();
    }

    /*
    * Find products by category
     */

    public List<ProductDto> findByCategory(int categoryId) {
        var products = productRepository.findProductsByCategory_Id(categoryId);
        if (products.isEmpty()) {
            return List.of();
        }
        return products.stream()
                .map(Product::toProductDto)
                .collect(Collectors.toList());
    }
}
