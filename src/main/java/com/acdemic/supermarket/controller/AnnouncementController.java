package com.acdemic.supermarket.controller;

import com.acdemic.supermarket.dto.request.AnnouncementRequest;
import com.acdemic.supermarket.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/announcement")
public class AnnouncementController {

    private final AnnouncementService service;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody AnnouncementRequest request){
        return ResponseEntity.status(201).body(service.save(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.status(200).body(service.get(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody AnnouncementRequest request, @PathVariable Long id) {
        return ResponseEntity.status(202).body(service.edit(request, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@RequestBody AnnouncementRequest request, @PathVariable Long id) {
        return ResponseEntity.status(204).body(service.delete(id));
    }

}
