package com.educative.ecommerce.controllers;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educative.ecommerce.config.ApiResponse;
import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/category")

public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @PostMapping("/create")
  public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
    if (Objects.nonNull(categoryService.readCategory(category.getCategoryName()))) {
      return new ResponseEntity<>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
    }
    categoryService.createCategory(category);
    return new ResponseEntity<>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
  }
}