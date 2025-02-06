package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.ReqAddCategoryDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Category;
import com.korit.springboot_study.repository.CategoryRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public SuccessResponseDto<List<Category>> findCategoriesAll() throws NotFoundException {
        List<Category> foundCategories = categoryRepository.findCategoryAll()
                .orElseThrow(() -> new NotFoundException("카테고리 데이터가 존재하지 않습니다."));

        return new SuccessResponseDto<>(foundCategories);
    }

    public SuccessResponseDto<List<Category>> findCategoriesByName(String categoryName) throws NotFoundException {
        List<Category> foundCategories = categoryRepository.findCategoryByName(categoryName)
                .orElseThrow(() -> new NotFoundException("카테고리 데이터가 존재하지 않습니다."));

        return new SuccessResponseDto<>(foundCategories);
    }

    public SuccessResponseDto<Category> saveCategory(ReqAddCategoryDto reqAddCategoryDto) throws DuplicateKeyException {
        return new SuccessResponseDto<>(
                categoryRepository
                        .saveCategory(new Category(0, reqAddCategoryDto.getCategoryName()))
                        .orElseThrow()
        );
    }

}
