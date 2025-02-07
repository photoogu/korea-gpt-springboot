package com.korit.springboot_study.dto.request;

import com.korit.springboot_study.entity.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReqAddCategoryDto {
    @ApiModelProperty(value = "카테고리명", required = true)
    private String categoryName;

    public Category toCategory() {
        return Category.builder()
                .categoryName(categoryName)
                .build();
    }
}
