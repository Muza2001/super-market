package com.acdemic.supermarket.service.impl;

import com.acdemic.supermarket.dto.request.ProductPhotoRequest;
import com.acdemic.supermarket.dto.response.ProductPhotoResponse;
import com.acdemic.supermarket.dto.response.Response;
import com.acdemic.supermarket.entity.Product;
import com.acdemic.supermarket.entity.ProductPhoto;
import com.acdemic.supermarket.mapper.CategoryMapper;
import com.acdemic.supermarket.mapper.ProductMapper;
import com.acdemic.supermarket.mapper.ProductPhotoMapper;
import com.acdemic.supermarket.repository.ProductPhotoRepository;
import com.acdemic.supermarket.repository.ProductRepository;
import com.acdemic.supermarket.service.ProductPhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class ProductPhotoServiceImpl implements ProductPhotoService {

    private final ProductPhotoRepository productPhotoRepository;
    private final ProductRepository productRepository;
    private final ProductPhotoMapper productPhotoMapper;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public ResponseEntity<?> save(ProductPhotoRequest request) {
        Product product = productRepository.findById(request.getProductId()).orElseThrow(()
                -> new IllegalArgumentException("Product not found"));
        ProductPhotoResponse response =
                productPhotoMapper.toResponse(productPhotoRepository
                                .save(productPhotoMapper
                        .toEntity(request,
                                  product)),
                productMapper
                        .toResponse(
                                product,
                                categoryMapper.toResponse(product.getCategory())));
        return ResponseEntity.status(201).body(new Response(
                "Product photo successfully saved !!!",
                true,
                response));
    }

    @Override
    public ResponseEntity<?> edit(ProductPhotoRequest request, Long id) {
        ProductPhoto productPhoto = productPhotoRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Id not found"));
        Product product = productRepository.findById(request.getProductId()).orElseThrow(()
                -> new IllegalArgumentException("Product not found"));
        if (!productPhoto.getExtension().equals(request.getExtension()))
            productPhoto.setExtension(request.getExtension());

        if (!productPhoto.getKey().equals(request.getKey()))
            productPhoto.setKey(request.getKey());

        if (!productPhoto.getPath().equals(request.getPath()))
            productPhoto.setPath(request.getPath());

        if (!productPhoto.getSize().equals(request.getSize()))
            productPhoto.setSize(request.getSize());

        if (!productPhoto.getOriginName().equals(request.getOriginName()))
            productPhoto.setOriginName(request.getOriginName());

        if (!productPhoto.getProduct().equals(product))
            productPhoto.setProduct(product);
        ProductPhotoResponse response =
                productPhotoMapper.toResponse(productPhotoRepository
                                .save(productPhotoMapper
                                        .toEntity(request,
                                                product)),
                        productMapper
                                .toResponse(
                                        product,
                                        categoryMapper.toResponse(product.getCategory())));
        return ResponseEntity.status(202).body(new Response(
                "Product photo successfully saved !!!",
                true,
                response));
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        ProductPhoto productPhoto = productPhotoRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Id not found"));
        ProductPhotoResponse response =
                productPhotoMapper.toResponse(productPhoto,
                productMapper.toResponse(productPhoto.getProduct(),
                categoryMapper.toResponse(productPhoto.getProduct().getCategory())));
        return ResponseEntity.status(202).body(new Response(
                "Product photo successfully saved !!!",
                true,
                response));
    }
    @Override
    public ResponseEntity<?> delete(Long id) {
        productPhotoRepository.delete(productPhotoRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Id not found")));
        return ResponseEntity.status(204).body(new Response(
                "Product photo successfully deleted !!!",
                true));
    }
}
