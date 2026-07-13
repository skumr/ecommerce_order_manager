package com.sudarshan.kumar.sb_poc_2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sudarshan.kumar.sb_poc_2.exceptions.ResourceNotFoundException;
import com.sudarshan.kumar.sb_poc_2.models.Product;
import com.sudarshan.kumar.sb_poc_2.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product currProduct = getProductById(id);

        currProduct.setName(updatedProduct.getName());
        currProduct.setPrice(updatedProduct.getPrice());
        currProduct.setQuantity(updatedProduct.getQuantity());
        currProduct.setSupplier(updatedProduct.getSupplier());

        return productRepository.save(currProduct);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}