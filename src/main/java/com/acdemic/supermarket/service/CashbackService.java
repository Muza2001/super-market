package com.acdemic.supermarket.service;

import com.acdemic.supermarket.dto.request.CashbackRequest;
import org.springframework.http.ResponseEntity;

public interface CashbackService {

    ResponseEntity<?> save(CashbackRequest request);

    ResponseEntity<?> edit(CashbackRequest request, Long id);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> delete(Long id);

}
