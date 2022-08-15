package com.acdemic.supermarket.controller;

import com.acdemic.supermarket.dto.request.ProductRequest;
import com.acdemic.supermarket.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService service;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductRequest request){
        return ResponseEntity.status(201).body(service.save(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.status(200).body(service.get(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody ProductRequest request, @PathVariable Long id) {
        return ResponseEntity.status(203).body(service.edit(request,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.status(204).body(service.delete(id));
    }
}
