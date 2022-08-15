package com.acdemic.supermarket.controller;

import com.acdemic.supermarket.dto.request.OrderRequest;
import com.acdemic.supermarket.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService service;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody OrderRequest request){
        return ResponseEntity.status(201).body(service.save(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.status(200).body(service.get(id));
    }

    @GetMapping("/saw")
    public ResponseEntity<?> saw(@RequestBody String username){
        return ResponseEntity.status(200).body(service.sawOrderDetails(username));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody OrderRequest request, @PathVariable Long id) {
        return ResponseEntity.status(203).body(service.edit(request, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.status(204).body(service.delete(id));
    }
}
