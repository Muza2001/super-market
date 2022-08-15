package com.acdemic.supermarket.service;

import com.acdemic.supermarket.dto.request.OrderRequest;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<?> sawOrderDetails(String username);

    ResponseEntity<?> save(OrderRequest request);

    ResponseEntity<?> edit(OrderRequest request, Long id);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> delete(Long id);

}
