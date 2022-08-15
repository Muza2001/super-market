package com.acdemic.supermarket.service;

import com.acdemic.supermarket.dto.request.AnnouncementRequest;
import org.springframework.http.ResponseEntity;

public interface AnnouncementService {
    ResponseEntity<?> save(AnnouncementRequest request);

    ResponseEntity<?> edit(AnnouncementRequest request, Long id);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> delete(Long id);
}
