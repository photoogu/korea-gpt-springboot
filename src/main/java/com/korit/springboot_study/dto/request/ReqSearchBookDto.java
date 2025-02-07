package com.korit.springboot_study.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqSearchBookDto {
    @ApiModelProperty(value = "도서명", example = "김수한무거북이와두루미책", required = false)
    private String keyword = "";
}
