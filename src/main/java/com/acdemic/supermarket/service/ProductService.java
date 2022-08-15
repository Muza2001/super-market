package com.acdemic.supermarket.service;

import com.acdemic.supermarket.dto.request.ProductRequest;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<?> save(ProductRequest request);

    ResponseEntity<?> edit(ProductRequest request, Long id);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> delete(Long id);


}
