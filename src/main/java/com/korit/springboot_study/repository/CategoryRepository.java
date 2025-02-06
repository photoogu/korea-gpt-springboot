package com.korit.springboot_study.repository;

import com.korit.springboot_study.entity.Category;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CategoryRepository {

    @Autowired
    private CategoryMapper categoryMapper;

    public Optional<List<Category>> findCategoryAll() {
        List<Category> foundCategories = categoryMapper.selectCategoryAll();

        return foundCategories.isEmpty()
                ? Optional.empty()
                : Optional.ofNullable(foundCategories);
    }

    public Optional<List<Category>> findCategoryByName(String categoryName) {
        List<Category> foundCategories = categoryMapper.selectCategoryByName(categoryName);

        return foundCategories.isEmpty()
                ? Optional.empty()
                : Optional.ofNullable(foundCategories);
    }

    public Optional<Category> saveCategory(Category category) throws DuplicateKeyException {
        try {
            categoryMapper.insertCategory(category);
        } catch (DuplicateKeyException e) {
            throw new CustomDuplicateKeyException(e.getMessage(), Map.of("categoryName", "이미 존재하는 카테고리명 입니다."));
        }

        return Optional.ofNullable(new Category(category.getCategoryId(), category.getCategoryName()));
    }
}
