package com.korit.springboot_study.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReqAddAuthorDto {
    @ApiModelProperty(value = "저자명" , required = true)
    private String authorName;
}
