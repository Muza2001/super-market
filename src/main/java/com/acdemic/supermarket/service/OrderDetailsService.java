package com.acdemic.supermarket.service;

import com.acdemic.supermarket.dto.request.OrderDetailsRequest;
import org.springframework.http.ResponseEntity;

public interface OrderDetailsService {

    ResponseEntity<?> save(OrderDetailsRequest request);

    ResponseEntity<?> edit(OrderDetailsRequest request, Long id);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> delete(Long id);

}
