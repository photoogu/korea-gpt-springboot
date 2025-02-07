package com.korit.springboot_study.dto.request;

import com.korit.springboot_study.entity.Author;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReqAddAuthorDto {
    @ApiModelProperty(value = "저자명" , required = true)
    private String authorName;

    public Author toAuthor() {
        return Author.builder()
                .authorName(authorName)
                .build();
    }
}
