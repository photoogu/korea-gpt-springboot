package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.ReqAddCategoryDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Category;
import com.korit.springboot_study.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "api/categories")
    @ApiOperation(value = "카테고리 정보 조회")
    public ResponseEntity<SuccessResponseDto<List<Category>>> getCategories(
            @RequestParam(value = "카테고리명", required = false)
            String categoryName
    ) throws NotFoundException {
        SuccessResponseDto<List<Category>> foundCategories = null;
        if (categoryName != null) {
            foundCategories = categoryService.findCategoriesByName(categoryName);
        } else {
            foundCategories = categoryService.findCategoriesAll();
        }

        return ResponseEntity.ok().body(foundCategories);
    }

    @PostMapping(value = "api/category")
    @ApiOperation(value = "카테고리 추가")
    public ResponseEntity<SuccessResponseDto<Category>> saveCategory(
            @RequestBody ReqAddCategoryDto reqAddCategoryDto
            ) {
        return ResponseEntity.ok().body(categoryService.saveCategory(reqAddCategoryDto));
    }
}
