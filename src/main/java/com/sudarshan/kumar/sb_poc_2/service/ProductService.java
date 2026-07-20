package com.sudarshan.kumar.sb_poc_2.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudarshan.kumar.sb_poc_2.dto.ProductDto;
import com.sudarshan.kumar.sb_poc_2.exceptions.InsufficientResourceException;
import com.sudarshan.kumar.sb_poc_2.exceptions.ResourceNotFoundException;
import com.sudarshan.kumar.sb_poc_2.mapper.ProductMapper;
import com.sudarshan.kumar.sb_poc_2.models.Product;
import com.sudarshan.kumar.sb_poc_2.models.Supplier;
import com.sudarshan.kumar.sb_poc_2.repositories.ProductRepository;
import com.sudarshan.kumar.sb_poc_2.repositories.SupplierRepository;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final ProductMapper productMapper;

    public ProductService(
            ProductRepository productRepository,
            SupplierRepository supplierRepository,
            ProductMapper productMapper
    ) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.productMapper = productMapper;
    }


    public List<ProductDto> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public ProductDto getProductById(Long id) {

        return productMapper.toDto(getProduct(id));
    }


    public ProductDto getProductByName(String name) {

        Product product = productRepository.findByNameIgnoreCase(name)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", name));

        return productMapper.toDto(product);
    }


    public List<ProductDto> getProductsBySupplier(Supplier supplier) {

        List<Product> products =
                productRepository.findBySupplier(supplier);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Products", supplier);
        }

        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductDto> getProductsAbovePrice(BigDecimal price) {

        return productRepository.findByPriceGreaterThan(price)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductDto> getProductsBelowPrice(BigDecimal price) {

        return productRepository.findByPriceLessThan(price)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductDto> getProductsWithinPriceRange(
            BigDecimal minPrice,
            BigDecimal maxPrice) {

        return productRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductDto> getLowStockProducts(int threshold) {

        return productRepository.findByQuantityLessThan(threshold)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductDto> getProductsInStock(int minimumQuantity) {

        return productRepository.findByQuantityGreaterThan(minimumQuantity)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductDto> getSupplierInventory(
            Supplier supplier,
            int minimumQuantity) {

        return productRepository.findBySupplierAndQuantityGreaterThan(
                    supplier,
                    minimumQuantity)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductDto> searchProducts(String keyword) {

        List<Product> products =
                productRepository.findByNameContainingIgnoreCase(keyword);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Products", keyword);
        }

        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductDto> getProductsStartingWith(String prefix) {

        return productRepository.findByNameStartingWithIgnoreCase(prefix)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductDto> getProductsByLowestPrice() {

        return productRepository.findByOrderByPriceAsc()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductDto> getProductsByHighestPrice() {

        return productRepository.findByOrderByPriceDesc()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductDto> getProductsByInventory() {

        return productRepository.findByOrderByQuantityDesc()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    @Transactional
    public ProductDto createProduct(ProductDto productDto) {

        if (productRepository.existsByNameIgnoreCase(productDto.getName())) {
            throw new IllegalArgumentException(
                    "A product with the name '" +
                    productDto.getName() +
                    "' already exists.");
        }

        Product product = productMapper.toEntity(productDto);

        if (productDto.getSupplier() != null) {

            Supplier supplier = getSupplier(
                    productDto.getSupplier().getId()
            );

            product.setSupplier(supplier);
        }

        Product savedProduct = productRepository.save(product);

        return productMapper.toDto(savedProduct);
    }


    @Transactional
    public ProductDto updateProduct(
            Long id,
            ProductDto updatedProductDto) {

        Product product = getProduct(id);

        product.setName(updatedProductDto.getName());
        product.setPrice(updatedProductDto.getPrice());
        product.setQuantity(updatedProductDto.getQuantity());

        if (updatedProductDto.getSupplier() != null) {

            Supplier supplier = getSupplier(
                    updatedProductDto.getSupplier().getId()
            );

            product.setSupplier(supplier);
        }

        return productMapper.toDto(product);
    }


    @Transactional
    public void deleteProduct(Long id) {

        productRepository.delete(getProduct(id));
    }


    @Transactional
    public void changePrice(Long id, BigDecimal newPrice) {

        if (newPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Price cannot be negative.");
        }

        Product product = getProduct(id);

        product.setPrice(newPrice);
    }


    @Transactional
    public void reduceInventory(Long id, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero.");
        }

        Product product = getProduct(id);

        if (product.getQuantity() < quantity) {
            throw new InsufficientResourceException(
                    "Product",
                    product.getName(),
                    id);
        }

        product.setQuantity(product.getQuantity() - quantity);
    }


    @Transactional
    public void increaseInventory(Long id, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero.");
        }

        Product product = getProduct(id);

        product.setQuantity(product.getQuantity() + quantity);
    }


    private Product getProduct(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", id));
    }


    private Supplier getSupplier(Long id) {

        return supplierRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Supplier", id));
    }
}
