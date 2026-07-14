package com.sudarshan.kumar.sb_poc_2.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudarshan.kumar.sb_poc_2.exceptions.InsufficientResourceException;
import com.sudarshan.kumar.sb_poc_2.exceptions.ResourceNotFoundException;
import com.sudarshan.kumar.sb_poc_2.models.Product;
import com.sudarshan.kumar.sb_poc_2.models.Supplier;
import com.sudarshan.kumar.sb_poc_2.repositories.ProductRepository;

@Service
@Transactional(readOnly = true)
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

    public Product getProductByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Product", name));
    }

    public List<Product> getProductsBySupplier(Supplier supplier) {
        List<Product> products = productRepository.findBySupplier(supplier);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Products", supplier);
        }

        return products;
    }

    public List<Product> getProductsAbovePrice(BigDecimal price) {
        return productRepository.findByPriceGreaterThan(price);
    }

    public List<Product> getProductsBelowPrice(BigDecimal price) {
        return productRepository.findByPriceLessThan(price);
    }

    public List<Product> getProductsWithinPriceRange(
            BigDecimal minPrice,
            BigDecimal maxPrice) {

        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> getLowStockProducts(int threshold) {
        return productRepository.findByQuantityLessThan(threshold);
    }

    public List<Product> getProductsInStock(int minimumQuantity) {
        return productRepository.findByQuantityGreaterThan(minimumQuantity);
    }

    public List<Product> getSupplierInventory(
            Supplier supplier,
            int minimumQuantity) {

        return productRepository.findBySupplierAndQuantityGreaterThan(
                supplier,
                minimumQuantity);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    public List<Product> getProductsStartingWith(String prefix) {
        return productRepository.findByNameStartingWithIgnoreCase(prefix);
    }

    public List<Product> getProductsByLowestPrice() {
        return productRepository.findByOrderByPriceAsc();
    }

    public List<Product> getProductsByHighestPrice() {
        return productRepository.findByOrderByPriceDesc();
    }

    public List<Product> getProductsByInventory() {
        return productRepository.findByOrderByQuantityDesc();
    }

    @Transactional
    public Product createProduct(Product product) {

        if (productRepository.existsByName(product.getName())) {
            throw new IllegalArgumentException(
                    "A product with the name '" + product.getName() + "' already exists.");
        }

        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, Product updatedProduct) {

        Product currentProduct = getProductById(id);

        currentProduct.setName(updatedProduct.getName());
        currentProduct.setPrice(updatedProduct.getPrice());
        currentProduct.setQuantity(updatedProduct.getQuantity());
        currentProduct.setSupplier(updatedProduct.getSupplier());

        return productRepository.save(currentProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    @Transactional
    public void changePrice(Long id, BigDecimal newPrice) {

        if (newPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        Product product = getProductById(id);

        product.setPrice(newPrice);

        productRepository.save(product);
    }

    @Transactional
    public void reduceInventory(Long id, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero.");
        }

        Product product = getProductById(id);

        if (product.getQuantity() < quantity) {
            throw new InsufficientResourceException(
                    "Product",
                    product.getName(),
                    id);
        }

        product.setQuantity(product.getQuantity() - quantity);

        productRepository.save(product);
    }

    @Transactional
    public void increaseInventory(Long id, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero.");
        }

        Product product = getProductById(id);

        product.setQuantity(product.getQuantity() + quantity);

        productRepository.save(product);
    }
}