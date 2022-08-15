package com.acdemic.supermarket.controller;

import com.acdemic.supermarket.dto.request.AnnouncementRequest;
import com.acdemic.supermarket.dto.request.OrderDetailsRequest;
import com.acdemic.supermarket.service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order_details")
public class OrderDetailsController {

    private final OrderDetailsService service;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody OrderDetailsRequest request){
        return ResponseEntity.status(201).body(service.save(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.status(200).body(service.get(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody OrderDetailsRequest request, @PathVariable Long id) {
        return ResponseEntity.status(203).body(service.edit(request, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.status(204).body(service.delete(id));
    }
}
