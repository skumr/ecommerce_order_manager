package com.sudarshan.kumar.sb_poc_2.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sudarshan.kumar.sb_poc_2.dto.ProductDto;
import com.sudarshan.kumar.sb_poc_2.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/allproducts")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }


    @GetMapping("/name/{name}")
    public ProductDto getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(
            @RequestBody ProductDto productDto) {

        return productService.createProduct(productDto);
    }


    @PutMapping("/{id}")
    public ProductDto updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDto productDto) {

        return productService.updateProduct(id, productDto);
    }


    @PatchMapping("/{id}/price")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePrice(
            @PathVariable Long id,
            @RequestParam BigDecimal price) {

        productService.changePrice(id, price);
    }


    @PatchMapping("/{id}/inventory/reduce")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reduceInventory(
            @PathVariable Long id,
            @RequestParam int quantity) {

        productService.reduceInventory(id, quantity);
    }


    @PatchMapping("/{id}/inventory/increase")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void increaseInventory(
            @PathVariable Long id,
            @RequestParam int quantity) {

        productService.increaseInventory(id, quantity);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
    }
}
