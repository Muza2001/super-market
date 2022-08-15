package com.acdemic.supermarket.service.impl;

import com.acdemic.supermarket.dto.request.OrderRequest;
import com.acdemic.supermarket.dto.response.OrderDetailsResponse;
import com.acdemic.supermarket.dto.response.OrderResponse;
import com.acdemic.supermarket.dto.response.Response;
import com.acdemic.supermarket.entity.Order;
import com.acdemic.supermarket.entity.OrderDetails;
import com.acdemic.supermarket.entity.User;
import com.acdemic.supermarket.mapper.CategoryMapper;
import com.acdemic.supermarket.mapper.OrderDetailsMapper;
import com.acdemic.supermarket.mapper.OrderMapper;
import com.acdemic.supermarket.mapper.ProductMapper;
import com.acdemic.supermarket.repository.OrderDetailsRepository;
import com.acdemic.supermarket.repository.OrderRepository;
import com.acdemic.supermarket.repository.UserRepository;
import com.acdemic.supermarket.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;
    private final OrderDetailsRepository orderDetailsRepository;
    private final UserRepository userRepository;
    private final CategoryMapper categoryMapper;
    private final OrderDetailsMapper orderDetailsMapper;

    public ResponseEntity<?> sawOrderDetails(String username){
        User user = userRepository.findByUsername(username).orElseThrow(()
                -> new IllegalArgumentException("Username not found"));
        Set<OrderDetails> details = orderDetailsRepository.findByUserId(user.getId()).orElseThrow(()
                -> new IllegalArgumentException("Order details not found"));
        Double quantity = null;
        Set<OrderDetailsResponse> responses = new HashSet<>();
        for (OrderDetails d : details) {
            if (d != null) {
                quantity += d.getQuantity();
                responses.add(
                        orderDetailsMapper.toResponse(d,
                                productMapper.toResponse(d.getProducts(),
                                        categoryMapper.toResponse(d.getProducts().getCategory()))));
            }
        }
        Order order = orderMapper.toEntity(
                details.size(),
                quantity,
                details);
        OrderResponse response =
                orderMapper.toResponse(
                        order,
                        responses) ;
        return ResponseEntity.status(201).body(new Response(
                "Order successfully saved !!!",
                true,
                response));
    }

    @Override
    public ResponseEntity<?> save(OrderRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()
                -> new IllegalArgumentException("Username not found"));
        Set<OrderDetails> details = orderDetailsRepository.findByUserId(user.getId()).orElseThrow(()
                -> new IllegalArgumentException("Order details not found"));
        Double quantity = null;
        Set<OrderDetailsResponse> responses = new HashSet<>();
        for (OrderDetails d : details) {
            if (d != null) {
                quantity += d.getQuantity();
                responses.add(
                        orderDetailsMapper.toResponse(d,
                        productMapper.toResponse(d.getProducts(),
                        categoryMapper.toResponse(d.getProducts().getCategory()))));
            }
        }
        OrderResponse response =
                orderMapper.toResponse(
                        orderRepository
                                .save(orderMapper.toEntity(
                                        details.size(),
                                        quantity,
                                        details)),
                        responses) ;
        return ResponseEntity.status(201).body(new Response(
                "Order successfully saved !!!",
                true,
                response));
    }

    @Override
    public ResponseEntity<?> edit(OrderRequest request, Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Id not found"));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()
                -> new IllegalArgumentException("Username not found"));
        Double quantity = null;
        Set<OrderDetailsResponse> responses = new HashSet<>();
        for (OrderDetails d : order.getOrderDetails()) {
            if (d != null) {
                quantity += d.getQuantity();
                responses.add(
                        orderDetailsMapper.toResponse(d,
                                productMapper.toResponse(d.getProducts(),
                                        categoryMapper.toResponse(d.getProducts().getCategory()))));
            }
        }
        if (!order.getQuantity().equals(quantity))
            order.setQuantity(quantity);
        if (!order.getOrderDetails().equals(order.getOrderDetails()))
            order.setOrderDetails(order.getOrderDetails());
        if (!order.getProductCount().equals(order.getOrderDetails().size()))
            order.setProductCount(order.getOrderDetails().size());

        OrderResponse response =
                orderMapper.toResponse(
                        orderRepository.save(order),
                        responses);
        return ResponseEntity.status(203).body(
                new Response(
                        "Order edited !!!",
                        true,
                        response));
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("ID not found"));
        Double quantity = null;
        Set<OrderDetailsResponse> responses = new HashSet<>();
        for (OrderDetails d : order.getOrderDetails()) {
            if (d != null) {
                quantity += d.getQuantity();
                responses.add(
                        orderDetailsMapper.toResponse(d,
                                productMapper.toResponse(d.getProducts(),
                                        categoryMapper.toResponse(d.getProducts().getCategory()))));
            }
        }
        OrderResponse response =
                orderMapper.toResponse(
                        orderRepository.save(order),
                        responses);
        return ResponseEntity.ok().body(new Response(
                "Find order",
                true,
                response));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        orderRepository.delete(orderRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("ID not found")));
        return ResponseEntity.ok().body(new Response("Successfully deleted !!!", true));
    }
}
