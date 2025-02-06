package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> selectCategoryAll();
    List<Category> selectCategoryByName(String categoryName);
    int insertCategory(Category category);
}
