package com.acdemic.supermarket.service.impl;

import com.acdemic.supermarket.dto.request.CategoryRequest;
import com.acdemic.supermarket.dto.response.CategoryResponse;
import com.acdemic.supermarket.dto.response.Response;
import com.acdemic.supermarket.entity.Category;
import com.acdemic.supermarket.mapper.*;
import com.acdemic.supermarket.repository.CategoryRepository;
import com.acdemic.supermarket.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    @Override
    public ResponseEntity<?> save(CategoryRequest request) {
        if (request.getParent() != null) {
            Category parent = categoryRepository.findById(request.getParent()).orElseThrow(()
                    -> new IllegalArgumentException("Parent id not found"));
            CategoryResponse categoryResponse =
                    categoryMapper.toResponse(
                            categoryRepository
                                    .save(categoryMapper.toEntityAndParent(request, parent)));
            return ResponseEntity.status(201).body(new Response(
                    "Category successfully saved !!!",
                    true,
                    categoryResponse));
        }
        else {
            CategoryResponse categoryResponse =
                    categoryMapper.toResponse(
                            categoryRepository
                                    .save(categoryMapper.toEntity(request)));
            return ResponseEntity.status(201).body(new Response(
                    "Category successfully saved !!!",
                    true,
                    categoryResponse));
        }
    }



    @Override
    public ResponseEntity<?> edit(CategoryRequest request, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Id not found"));
        if (!category.getName().equals(request.getName()))
            category.setName(request.getName());
        Category parent = categoryRepository.findById(request.getParent())
                .orElseThrow(() -> new IllegalArgumentException("Parent id not found"));
        if (!category.getParent().equals(parent))
            category.setParent(parent);
        CategoryResponse categoryResponse =
                categoryMapper.toResponse(categoryRepository.save(category));
        return ResponseEntity.status(203).body(new Response(
                "Successfully edited !!!",
                true,
                categoryResponse));
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        CategoryResponse response =
                categoryMapper.toResponse(categoryRepository.findById(id).orElseThrow(()
                        -> new IllegalArgumentException("Id not found")));
        return ResponseEntity.status(200).body(new Response("Find category", true, response));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        categoryRepository.delete(
                categoryRepository
                        .findById(id).orElseThrow(()
                            -> new IllegalArgumentException("Id not found")));
        return ResponseEntity.status(204).body(new Response("Category successfully deleted !!!", true));
    }
}
