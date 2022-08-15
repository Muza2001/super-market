package com.acdemic.supermarket.service;

import com.acdemic.supermarket.dto.request.AnnouncementPhotoRequest;
import org.springframework.http.ResponseEntity;

public interface AnnouncementPhotoService {

    ResponseEntity<?> save(AnnouncementPhotoRequest request);

    ResponseEntity<?> edit(AnnouncementPhotoRequest request, Long id);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> delete(Long id);

}
