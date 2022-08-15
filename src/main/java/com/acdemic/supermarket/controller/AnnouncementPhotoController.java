package com.acdemic.supermarket.controller;

import com.acdemic.supermarket.dto.request.AnnouncementPhotoRequest;
import com.acdemic.supermarket.service.AnnouncementPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/announcement_photo")
public class AnnouncementPhotoController {
    private final AnnouncementPhotoService service;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody AnnouncementPhotoRequest request){
        return ResponseEntity.status(201).body(service.save(request));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.status(200).body(service.get(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody AnnouncementPhotoRequest request, @PathVariable Long id) {
        return ResponseEntity.status(203).body(service.edit(request, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.status(204).body(service.delete(id));
    }

}
