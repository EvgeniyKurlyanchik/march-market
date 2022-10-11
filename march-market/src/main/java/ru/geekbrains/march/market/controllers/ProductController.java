package ru.geekbrains.march.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.dtos.CreateNewProductDto;
import ru.geekbrains.march.market.dtos.ProductDto;
import ru.geekbrains.march.market.entities.Product;
import ru.geekbrains.march.market.entities.ProductConverter;
import ru.geekbrains.march.market.exceptions.AppError;
import ru.geekbrains.march.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private ProductConverter productConverter;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
//        Optional<Product> p = productService.findById(id);
//        if (p.isPresent()) {
//            return new ResponseEntity<>(productConverter.entityToDto(p.get()), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new AppError("RESOURCE_NOT_FOUND", "Продукт с id: " + id + " не найден"), HttpStatus.NOT_FOUND);
        return productConverter.entityToDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + id + " не найден")));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProducts(@RequestBody CreateNewProductDto createNewProductDto) {
        productService.createNewProduct(createNewProductDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
