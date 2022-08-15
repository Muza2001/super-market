package com.acdemic.supermarket.service;

import com.acdemic.supermarket.dto.request.ShopRequest;
import org.springframework.http.ResponseEntity;

public interface ShopService {

    ResponseEntity<?> save(ShopRequest request);

    ResponseEntity<?> edit(ShopRequest request, Long id);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> delete(Long id);

}
