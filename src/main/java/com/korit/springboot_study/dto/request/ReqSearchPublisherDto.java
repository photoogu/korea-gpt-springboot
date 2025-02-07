package com.korit.springboot_study.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqSearchPublisherDto {
    @ApiModelProperty(value = "출판사명", example = "김수한무거북이와두루미출판사", required = false)
    private String keyword = "";
}
