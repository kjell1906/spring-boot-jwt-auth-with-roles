package com.example.demo.controllers;

import com.example.demo.services.ProductService;
import com.example.demo.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.listAll();
    }

    @GetMapping("get/{id}")
    public Product getProduct(@PathVariable(name = "id") int id){
        return productService.get(id);

    }

    @PostMapping("/new")

    public void createProduct(@RequestBody Product product){
        productService.save(product);
    }

    @PutMapping("update")
    public void updateProduct(@RequestBody Product product) {
        Product p = product;

        if(product == null){
            throw new IllegalStateException(String.format("Product with %s does not exists", product.getId()));
        }

        productService.save(p);
    }

    @DeleteMapping("remove/{id}")
    public void removeProduct(@PathVariable(name = "id") int id){
        productService.delete(id);
    }
}
