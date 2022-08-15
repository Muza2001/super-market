package com.acdemic.supermarket.service.impl;

import com.acdemic.supermarket.dto.request.ShopRequest;
import com.acdemic.supermarket.dto.response.Response;
import com.acdemic.supermarket.dto.response.ShopResponse;
import com.acdemic.supermarket.entity.Category;
import com.acdemic.supermarket.entity.Shop;
import com.acdemic.supermarket.entity.User;
import com.acdemic.supermarket.mapper.*;
import com.acdemic.supermarket.repository.CategoryRepository;
import com.acdemic.supermarket.repository.ShopRepository;
import com.acdemic.supermarket.repository.UserRepository;
import com.acdemic.supermarket.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final CashbackMapper cashbackMapper;

    @Override
    public ResponseEntity<?> save(ShopRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(()
                -> new IllegalArgumentException("User id not found"));
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()
                -> new IllegalArgumentException("Category id not found"));
        ShopResponse shopResponse = shopMapper.toResponse(
                shopRepository.save(
                        shopMapper
                                .toEntity(
                                        request,
                                        category,
                                        user)),
                userMapper
                        .toResponse(
                                user,
                                cashbackMapper
                                        .toResponse(
                                                user.getCashback()),
                        roleMapper
                                .toResponse(
                                        user.getRole())),
                categoryMapper
                        .toResponse(category));
        return ResponseEntity.status(201).body(new Response(
                "Shop successfully saved",
                true,
                shopResponse));
    }

    @Override
    public ResponseEntity<?> edit(ShopRequest request, Long id) {
        Shop shop = shopRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Shop id not found"));

        User user = userRepository.findById(request.getUserId()).orElseThrow(()
                -> new IllegalArgumentException("User id not found"));

        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()
                -> new IllegalArgumentException("Category id not found"));

        if (!shop.getFloor().equals(request.getFloor()))
            shop.setFloor(request.getFloor());

        if (!shop.getName().equals(request.getName()))
            shop.setName(request.getName());

        if (!shop.getCategory().equals(category))
            shop.setCategory(category);

        if (!shop.getUser().equals(user))
            shop.setUser(user);

        ShopResponse shopResponse = shopMapper.toResponse(
                shopRepository.save(shopRepository.save(shop)),
                userMapper
                        .toResponse(
                                user,
                                cashbackMapper
                                        .toResponse(
                                                user.getCashback()),
                                roleMapper
                                        .toResponse(
                                                user.getRole())),
                categoryMapper
                        .toResponse(category));
        return ResponseEntity.status(202).body(new Response(
                "Shop successfully edited",
                true,
                shopResponse));
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Shop shop = shopRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Shop id not found"));
        ShopResponse shopResponse =
                shopMapper
                        .toResponse(
                                shop,
                                userMapper
                                        .toResponse(
                                                shop.getUser(),
                                                cashbackMapper
                                                        .toResponse(
                                                                shop.getUser().getCashback()),
                                                roleMapper
                                                        .toResponse(shop.getUser().getRole())),
                                categoryMapper
                                        .toResponse(
                                                shop.getCategory())
                        );
        return ResponseEntity.status(200).body(new Response(
                "Shop successfully find",
                true,
                shopResponse));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        shopRepository.delete(shopRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Shop id not found")));
        return ResponseEntity.ok().body(new Response("Shop successfully deleted !!!", true));
    }
}
