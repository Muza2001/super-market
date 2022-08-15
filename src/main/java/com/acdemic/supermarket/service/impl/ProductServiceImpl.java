package com.acdemic.supermarket.service.impl;

import com.acdemic.supermarket.dto.request.ProductRequest;
import com.acdemic.supermarket.dto.response.ProductResponse;
import com.acdemic.supermarket.dto.response.Response;
import com.acdemic.supermarket.entity.Category;
import com.acdemic.supermarket.entity.Product;
import com.acdemic.supermarket.mapper.CategoryMapper;
import com.acdemic.supermarket.mapper.ProductMapper;
import com.acdemic.supermarket.repository.CategoryRepository;
import com.acdemic.supermarket.repository.ProductRepository;
import com.acdemic.supermarket.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    @Override
    public ResponseEntity<?> save(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()
                -> new IllegalArgumentException("Category id not found"));
        ProductResponse response = productMapper.toResponse(
                productRepository.save(
                productMapper.toEntity(request, category)),
                categoryMapper.toResponse(category)) ;
        return ResponseEntity.status(201).body(
                new Response(
                   "Product successfully added !!!",
                    true,
                           response));
    }

    @Override
    public ResponseEntity<?> edit(ProductRequest request, Long id) {
        Product product = productRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Product id not found"));
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()
                -> new IllegalArgumentException("Category id not found"));
        if (!product.getPrice().equals(request.getPrice()))
            product.setPrice(request.getPrice());

        if (!product.getDescription().equals(request.getDescription()))
            product.setDescription(request.getDescription());

        if (!product.getGaraty().equals(request.getGaraty()))
            product.setGaraty(request.getGaraty());

        if (!product.getName().equals(request.getName()))
            product.setName(request.getName());

        if (!product.getCategory().equals(category))
            product.setCategory(category);

        ProductResponse response = productMapper.toResponse(
                productRepository.save(
                        product),
                categoryMapper.toResponse(category)) ;
        return ResponseEntity.status(201).body(
                new Response(
                        "Product successfully added !!!",
                        true,
                        response));
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Product id not found"));
        Category category = categoryRepository.findById(product.getCategory().getId()).orElseThrow(()
                -> new IllegalArgumentException("Category id not found"));

        ProductResponse response = productMapper.toResponse(
                productRepository.save(
                        product),
                categoryMapper.toResponse(category)) ;
        return ResponseEntity.status(201).body(
                new Response(
                        "Product successfully added !!!",
                        true,
                        response));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        productRepository.delete(productRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Product id not found")));
        return ResponseEntity.status(204).body(new Response(
                "Product successfully deleted !!!",
                true));
    }
}
