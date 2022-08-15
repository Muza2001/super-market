package com.acdemic.supermarket.controller;

import com.acdemic.supermarket.dto.request.PaymentRequest;
import com.acdemic.supermarket.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService service;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PaymentRequest request){
        return null;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody PaymentRequest request, @PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return null;
    }
}
