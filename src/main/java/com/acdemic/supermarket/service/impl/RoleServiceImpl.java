package com.acdemic.supermarket.service.impl;

import com.acdemic.supermarket.dto.request.RoleRequest;
import com.acdemic.supermarket.dto.response.Response;
import com.acdemic.supermarket.dto.response.RoleResponse;
import com.acdemic.supermarket.entity.Role;
import com.acdemic.supermarket.mapper.RoleMapper;
import com.acdemic.supermarket.repository.RoleRepository;
import com.acdemic.supermarket.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public ResponseEntity<?> save(RoleRequest request) {
        RoleResponse response =
                roleMapper.toResponse(roleRepository.save(roleMapper.toEntity(request)));
        return ResponseEntity.status(201).body(new Response(
                "Role saved !!!",
                true,
                response));
    }

    @Override
    public ResponseEntity<?> edit(RoleRequest request, Long id) {
        Role role = roleRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Role id not found"));
        if (!role.getName().equals(request.getName()))
            role.setName(request.getName());
        RoleResponse response = roleMapper.toResponse(roleRepository.save(role));
        return ResponseEntity.ok().body(new Response(
                "Edited role !!!",
                true,
                response));
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Role id not found"));
        RoleResponse response = roleMapper.toResponse(roleRepository.save(role));
        return ResponseEntity.ok().body(new Response(
                "Find role !!!",
                true,
                response));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        roleRepository.delete(roleRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Role id not found")));
        return ResponseEntity.ok().body(new Response(
                "Role successfully deleted !!!",
                true));
    }
}
