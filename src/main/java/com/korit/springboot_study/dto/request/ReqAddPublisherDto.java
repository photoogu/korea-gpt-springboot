package com.korit.springboot_study.dto.request;

import com.korit.springboot_study.entity.Publisher;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReqAddPublisherDto {
    @ApiModelProperty(value = "출판사명" , required = true)
    private String publisherName;

    public Publisher toPublisher() {
        return Publisher.builder()
                .publisherName(publisherName)
                .build();
    }
}
