package com.acdemic.supermarket.service;

import com.acdemic.supermarket.dto.request.UserRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> save(UserRequest request);

    ResponseEntity<?> edit(UserRequest request, Long id);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> delete(Long id);

}
