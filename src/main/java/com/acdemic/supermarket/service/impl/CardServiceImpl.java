package com.acdemic.supermarket.service.impl;

import com.acdemic.supermarket.dto.request.CardRequest;
import com.acdemic.supermarket.dto.response.CardResponse;
import com.acdemic.supermarket.dto.response.Response;
import com.acdemic.supermarket.dto.response.UserResponse;
import com.acdemic.supermarket.entity.Card;
import com.acdemic.supermarket.entity.User;
import com.acdemic.supermarket.mapper.CardMapper;
import com.acdemic.supermarket.mapper.CashbackMapper;
import com.acdemic.supermarket.mapper.RoleMapper;
import com.acdemic.supermarket.mapper.UserMapper;
import com.acdemic.supermarket.repository.CardRepository;
import com.acdemic.supermarket.repository.UserRepository;
import com.acdemic.supermarket.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CashbackMapper cashbackMapper;
    private final RoleMapper roleMapper;

    @Override
    public ResponseEntity<?> save(CardRequest request) {
         Card card = cardRepository.save(cardMapper.toEntity(
                request,
                userRepository
                        .findById(request.getUserId()).orElseThrow(()
                             -> new IllegalArgumentException("User id not found"))));
         CardResponse cardResponse = cardMapper.toResponse(
                 card,
                 userMapper.toResponse(card.getUser(), // get UserResponse in userMapper
                 cashbackMapper.toResponse(card.getUser().getCashback()), // get CashbackResponse in CashbackMapper
                 roleMapper.toResponse(card.getUser().getRole()))); // get RoleResponse in RoleMapper
        return ResponseEntity.status(201).body(
                new Response(
                        "Card successfully saved !!!",
                        true,
                                cardResponse));
    }

    @Override
    public ResponseEntity<?> edit(CardRequest request, Long id) {
        Card card = cardRepository
                .findById(id).orElseThrow(()
                        -> new IllegalArgumentException("Id not found"));

        User user = userRepository
                .findById(request.getUserId()).orElseThrow(()
                        -> new IllegalArgumentException("User id not found"));

        if (!card.getBalance().equals(request.getBalance()))
            card.setBalance(request.getBalance());

        if (!card.getName().equals(request.getName()))
            card.setName(request.getName());

        if (!card.getNumber().equals(request.getNumber()))
            card.setNumber(request.getNumber());

        if (!card.getExpired_data().equals(request.getExpired_data()))
            card.setExpired_data(request.getExpired_data());

        if (!card.getUser().equals(user))
            card.setUser(user);

        CardResponse cardResponse = cardMapper.toResponse(
                cardRepository.save(card), // save new card
                userMapper.toResponse(card.getUser(), // get UserResponse in userMapper
                cashbackMapper.toResponse(card.getUser().getCashback()), // get CashbackResponse in CashbackMapper
                roleMapper.toResponse(card.getUser().getRole()))); // get RoleResponse in RoleMapper
        return ResponseEntity.status(HttpStatus.OK).body(new Response(
                "Successfully edited !!!",
                true,
                cardResponse));
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Id not found"));
        CardResponse cardResponse = cardMapper.toResponse(
                cardRepository.save(card), // save new card
                userMapper.toResponse(card.getUser(), // get UserResponse in userMapper
                cashbackMapper.toResponse(card.getUser().getCashback()), // get CashbackResponse in CashbackMapper
                roleMapper.toResponse(card.getUser().getRole()))); // get RoleResponse in RoleMapper
        return ResponseEntity.status(HttpStatus.OK).body(
                new Response(
                        "Find Card !!!",
                        true,
                        cardResponse));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        cardRepository.delete(cardRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Id not found")));
        return ResponseEntity.status(204).body(new Response("Card successfully deleted", true));
    }
}
