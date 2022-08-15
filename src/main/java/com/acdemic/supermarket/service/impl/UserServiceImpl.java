package com.acdemic.supermarket.service.impl;

import com.acdemic.supermarket.dto.request.UserRequest;
import com.acdemic.supermarket.dto.response.Response;
import com.acdemic.supermarket.dto.response.UserResponse;
import com.acdemic.supermarket.entity.Cashback;
import com.acdemic.supermarket.entity.Role;
import com.acdemic.supermarket.entity.User;
import com.acdemic.supermarket.mapper.CashbackMapper;
import com.acdemic.supermarket.mapper.RoleMapper;
import com.acdemic.supermarket.mapper.UserMapper;
import com.acdemic.supermarket.repository.CashbackRepository;
import com.acdemic.supermarket.repository.RoleRepository;
import com.acdemic.supermarket.repository.UserRepository;
import com.acdemic.supermarket.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CashbackMapper cashbackMapper;
    private final CashbackRepository cashbackRepository;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public ResponseEntity<?> save(UserRequest request) {
        Role role = roleRepository.findById(request.getRoleId()).orElseThrow(()
                -> new IllegalArgumentException("Role id not found"));
        Cashback cashback = cashbackRepository.findById(request.getCashbackId()).orElseThrow(()
                -> new IllegalArgumentException("Cashback id not found"));
        UserResponse response = userMapper.toResponse(userRepository.save(
                userMapper.toEntity(request,role,cashback)),
                cashbackMapper.toResponse(cashback),
                roleMapper.toResponse(role));
        return ResponseEntity.status(201).body(new Response(
                "User successfully save",
                true,
                response));
    }

    @Override
    public ResponseEntity<?> edit(UserRequest request, Long id) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("User id not found"));

        Role role = roleRepository.findById(request.getRoleId()).orElseThrow(()
                -> new IllegalArgumentException("Role id not found"));

        Cashback cashback = cashbackRepository.findById(request.getCashbackId()).orElseThrow(()
                -> new IllegalArgumentException("Cashback id not found"));

        if (!user.getUsername().equals(request.getUsername()))
            user.setUsername(request.getUsername());

        if (!user.getFirstname().equals(request.getFirstname()))
            user.setFirstname(request.getFirstname());

        if (!user.getLastname().equals(request.getLastname()))
            user.setLastname(request.getLastname());

        if (!user.getPassword().equals(request.getPassword()))
            user.setPassword(request.getPassword());

        if (!user.getPhoneNumber().equals(request.getPhoneNumber()))
            user.setPhoneNumber(request.getPhoneNumber());

        if (!user.getRole().equals(role))
            user.setRole(role);

        if (!user.getCashback().equals(cashback))
            user.setCashback(cashback);

        UserResponse response = userMapper.toResponse(userRepository.save(user),
                cashbackMapper.toResponse(cashback),
                roleMapper.toResponse(role));
        return ResponseEntity.ok().body(
                new Response(
               "User successfully edited !!!",
                true,
                       response));
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("User id not found"));

        UserResponse response = userMapper.toResponse(user,
                cashbackMapper.toResponse(user.getCashback()),
                roleMapper.toResponse(user.getRole()));
        return ResponseEntity.ok().body(
                new Response(
                        "User successfully edited !!!",
                        true,
                        response));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("User id not found")));
        return ResponseEntity.ok().body(new Response("User successfully deleted", true));
    }
}
