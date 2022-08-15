package com.acdemic.supermarket.service;

import com.acdemic.supermarket.dto.request.ProductPhotoRequest;
import org.springframework.http.ResponseEntity;

public interface ProductPhotoService {

    ResponseEntity<?> save(ProductPhotoRequest request);

    ResponseEntity<?> edit(ProductPhotoRequest request, Long id);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> delete(Long id);

}
