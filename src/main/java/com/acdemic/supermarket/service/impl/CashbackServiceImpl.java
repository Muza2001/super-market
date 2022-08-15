package com.acdemic.supermarket.service.impl;

import com.acdemic.supermarket.dto.request.CashbackRequest;
import com.acdemic.supermarket.dto.response.CashbackResponse;
import com.acdemic.supermarket.dto.response.Response;
import com.acdemic.supermarket.entity.Cashback;
import com.acdemic.supermarket.mapper.CashbackMapper;
import com.acdemic.supermarket.repository.CashbackRepository;
import com.acdemic.supermarket.service.CashbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class CashbackServiceImpl implements CashbackService {

    private final CashbackRepository cashbackRepository;
    private final CashbackMapper cashbackMapper;

    @Override
    public ResponseEntity<?> save(CashbackRequest request) {
        CashbackResponse cashbackResponse =
                cashbackMapper.toResponse(
                        cashbackRepository.save(
                                cashbackMapper.toEntity(request)) // save cashBack
                ); // this CashBackResponse
        return ResponseEntity.status(201).body(new Response(
                "Successfully saved !!!",
                true,
                cashbackResponse));
    }

    @Override
    public ResponseEntity<?> edit(CashbackRequest request, Long id) {
        Cashback cashback = cashbackRepository
                .findById(id).orElseThrow(()
                        -> new IllegalArgumentException("Id not found"));
        if (!cashback.getBalance().equals(request.getBalance()))
            cashback.setBalance(request.getBalance());
        if (!cashback.getName().equals(request.getName()))
            cashback.setName(request.getName());
        if (!cashback.getNumber().equals(request.getNumber()))
            cashback.setNumber(request.getNumber());
        return ResponseEntity.status(203).body(
                new Response(
                        "Cashback successfully edited !!!",
                         true,
                                cashbackMapper.toResponse(cashback)));
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Cashback cashback = cashbackRepository
                .findById(id).orElseThrow(()
                        -> new IllegalArgumentException("Id not found"));
        return ResponseEntity.status(200).body(
                new Response(
                        "Cashback successfully edited !!!",
                        true,
                        cashbackMapper.toResponse(cashback)));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        cashbackRepository.delete(cashbackRepository
                .findById(id).orElseThrow(()
                        -> new IllegalArgumentException("Id not found")));
        return ResponseEntity.ok().body(new Response("Successfully deleted !!!", true));
    }
}
