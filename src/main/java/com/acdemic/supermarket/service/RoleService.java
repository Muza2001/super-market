package com.acdemic.supermarket.service;

import com.acdemic.supermarket.dto.request.RoleRequest;
import org.springframework.http.ResponseEntity;

public interface RoleService {

    ResponseEntity<?> save(RoleRequest request);

    ResponseEntity<?> edit(RoleRequest request, Long id);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> delete(Long id);

}
