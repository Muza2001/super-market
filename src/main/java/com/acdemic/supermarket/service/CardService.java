package com.acdemic.supermarket.service;

import com.acdemic.supermarket.dto.request.CardRequest;
import org.springframework.http.ResponseEntity;

public interface CardService {

    ResponseEntity<?> save(CardRequest request);

    ResponseEntity<?> edit(CardRequest request, Long id);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> delete(Long id);

}
