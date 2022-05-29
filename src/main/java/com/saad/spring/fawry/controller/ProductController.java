package com.saad.spring.fawry.controller;

import com.saad.spring.fawry.model.Product;
import com.saad.spring.fawry.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Product> post(@RequestBody Product model) {
        return ResponseEntity.ok(service.save(model));
    }

    @PutMapping
    public ResponseEntity<Product> put(@RequestBody Product model) {
        return ResponseEntity.ok(service.update(model));
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<Product> activate(@PathVariable String id) {
        return ResponseEntity.ok(service.activate(id));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Product> deactivate(@PathVariable String id) {
        return ResponseEntity.ok(service.deactivate(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable String id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PostMapping("/import")
    public ResponseEntity<List<Product>> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(service.uploadFile(file));
    }

}
