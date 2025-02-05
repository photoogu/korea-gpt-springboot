package com.korit.springboot_study.entity.study;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Major {
    private int majorId;
    private String majorName;
}

/*
* @Data : getter, setter >> mapper 에서 insert 내의 #{majorName} 이 getMajorName() 호출한 것!
* @NoArgsConstructor, AllArgsConstructor >> select 내에서 setter 와 NoArgs, 그리고 AllArgs 이 필요!
* */
