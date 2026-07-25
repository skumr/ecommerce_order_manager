package com.sudarshan.kumar.sb_poc_2.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudarshan.kumar.sb_poc_2.dto.product.CreateProductRequestDto;
import com.sudarshan.kumar.sb_poc_2.dto.product.ProductResponseDto;
import com.sudarshan.kumar.sb_poc_2.dto.product.UpdateProductRequestDto;
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


    public List<ProductResponseDto> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public ProductResponseDto getProductById(Long id) {

        return productMapper.toDto(getProduct(id));
    }


    public ProductResponseDto getProductByName(String name) {

        Product product = productRepository.findByNameIgnoreCase(name)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", name));

        return productMapper.toDto(product);
    }


    public List<ProductResponseDto> getProductsBySupplier(Supplier supplier) {

        List<Product> products =
                productRepository.findBySupplier(supplier);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Products", supplier);
        }

        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductResponseDto> getProductsAbovePrice(BigDecimal price) {

        return productRepository.findByPriceGreaterThan(price)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductResponseDto> getProductsBelowPrice(BigDecimal price) {

        return productRepository.findByPriceLessThan(price)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductResponseDto> getProductsWithinPriceRange(
            BigDecimal minPrice,
            BigDecimal maxPrice) {

        return productRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductResponseDto> getLowStockProducts(int threshold) {

        return productRepository.findByQuantityLessThan(threshold)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductResponseDto> getProductsInStock(int minimumQuantity) {

        return productRepository.findByQuantityGreaterThan(minimumQuantity)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductResponseDto> getSupplierInventory(
            Supplier supplier,
            int minimumQuantity) {

        return productRepository.findBySupplierAndQuantityGreaterThan(
                    supplier,
                    minimumQuantity)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductResponseDto> searchProducts(String keyword) {

        List<Product> products =
                productRepository.findByNameContainingIgnoreCase(keyword);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Products", keyword);
        }

        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductResponseDto> getProductsStartingWith(String prefix) {

        return productRepository.findByNameStartingWithIgnoreCase(prefix)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductResponseDto> getProductsByLowestPrice() {

        return productRepository.findByOrderByPriceAsc()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductResponseDto> getProductsByHighestPrice() {

        return productRepository.findByOrderByPriceDesc()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    public List<ProductResponseDto> getProductsByInventory() {

        return productRepository.findByOrderByQuantityDesc()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }


    @Transactional
    public ProductResponseDto createProduct(CreateProductRequestDto productDto) {

        if (productRepository.existsByNameIgnoreCase(productDto.getName())) {
            throw new IllegalArgumentException(
                    "A product with the name '" +
                    productDto.getName() +
                    "' already exists.");
        }

        Product product = productMapper.toEntity(productDto);

        if (productDto.getSupplier() != null) {

            Supplier supplier = getSupplier(
                    productDto.getSupplierId());

            product.setSupplier(supplier);
        }

        Product savedProduct = productRepository.save(product);

        return productMapper.toDto(savedProduct);
    }


    @Transactional
    public ProductResponseDto updateProduct(
            Long id,
            UpdateProductRequestDto updatedProductDto) {

        Product product = getProduct(id);

        product.setName(updatedProductDto.getName());
        product.setPrice(updatedProductDto.getPrice());
        product.setQuantity(updatedProductDto.getQuantity());

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
