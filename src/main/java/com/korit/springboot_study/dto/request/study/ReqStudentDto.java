package com.korit.springboot_study.dto.request.study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor         // 모든 변수 포함 생성자
//@RequiredArgsConstructor    // final 변수 포함 생성자
@ApiModel(description = "학생정보 조회 학습 DTO")
public class ReqStudentDto {
    @NonNull
    @ApiModelProperty(value = "학생 이름", example = "김영경", required = true)
    private String name;
    @ApiModelProperty(value = "학생 나이", example = "26", required = false)
    private int age;
}
