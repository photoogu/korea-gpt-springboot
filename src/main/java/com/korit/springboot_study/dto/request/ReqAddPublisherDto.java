package com.korit.springboot_study.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReqAddPublisherDto {
    @ApiModelProperty(value = "출판사명" , required = true)
    private String publisherName;
}
