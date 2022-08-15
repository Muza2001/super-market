package com.acdemic.supermarket.service;

import com.acdemic.supermarket.dto.request.CategoryRequest;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity<?> save(CategoryRequest request);

    ResponseEntity<?> edit(CategoryRequest request, Long id);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> delete(Long id);

}
