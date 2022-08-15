package com.acdemic.supermarket.service.impl;

import com.acdemic.supermarket.dto.request.OrderDetailsRequest;
import com.acdemic.supermarket.dto.response.OrderDetailsResponse;
import com.acdemic.supermarket.dto.response.ProductResponse;
import com.acdemic.supermarket.dto.response.Response;
import com.acdemic.supermarket.entity.OrderDetails;
import com.acdemic.supermarket.entity.Product;
import com.acdemic.supermarket.entity.User;
import com.acdemic.supermarket.mapper.CategoryMapper;
import com.acdemic.supermarket.mapper.OrderDetailsMapper;
import com.acdemic.supermarket.mapper.ProductMapper;
import com.acdemic.supermarket.repository.OrderDetailsRepository;
import com.acdemic.supermarket.repository.ProductRepository;
import com.acdemic.supermarket.repository.UserRepository;
import com.acdemic.supermarket.service.OrderDetailsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailsMapper orderDetailsMapper;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;
    @Override
    public ResponseEntity<?> save(OrderDetailsRequest request) {
        Product product = productRepository.findById(request.getProductId()).orElseThrow(()
                -> new IllegalArgumentException("Product id not found"));
        User user = userRepository.findById(request.getUserId()).orElseThrow(()
                -> new IllegalArgumentException("User id not found"));
        OrderDetailsResponse response = orderDetailsMapper.toResponse(
                orderDetailsRepository.save(
                    orderDetailsMapper.toEntity(
                         product.getPrice(),
                 product.getPrice() * request.getCountOfProduct(),
                         product,
                        user)),
                productMapper.toResponse(product,
                categoryMapper.toResponse(product.getCategory())));
        return ResponseEntity.status(201).body(new Response(
                "Successfully saved !!!",
                 true,
                        response));
    }

    @Override
    public ResponseEntity<?> edit(OrderDetailsRequest request, Long id) {
        OrderDetails orderDetails = orderDetailsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Id not found"));

        Product product = productRepository.findById(request.getProductId()).orElseThrow(()
                -> new IllegalArgumentException("Product id not found"));

        User user = userRepository.findById(request.getUserId()).orElseThrow(()
                -> new IllegalArgumentException("User id not found"));

        if (!orderDetails.getCountOfProduct().equals(request.getCountOfProduct()))
            orderDetails.setCountOfProduct(request.getCountOfProduct());
        if (!orderDetails.getProducts().equals(product))
            orderDetails.setProducts(product);

        OrderDetailsResponse response = orderDetailsMapper.toResponse(
                orderDetailsRepository.save(
                        orderDetailsMapper.toEntity(
                                product.getPrice(),
                                product.getPrice() * request.getCountOfProduct(),
                                product,
                                user)),
                productMapper.toResponse(product,
                        categoryMapper.toResponse(product.getCategory())));
        return ResponseEntity.status(201).body(new Response(
                "Successfully saved !!!",
                true,
                response));
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        OrderDetails orderDetails = orderDetailsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Id not found"));
        OrderDetailsResponse response = orderDetailsMapper.toResponse(
                orderDetails,
                productMapper.toResponse(orderDetails.getProducts(),
                categoryMapper.toResponse(orderDetails.getProducts().getCategory())));
        return ResponseEntity.ok().body(new Response("Find order details", true, response));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        orderDetailsRepository.delete(orderDetailsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Id not found")));
        return ResponseEntity.ok().body(new Response("Successfully deleted !!!", true));
    }
}
