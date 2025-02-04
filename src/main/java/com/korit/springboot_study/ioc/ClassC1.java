package com.korit.springboot_study.ioc;

import org.springframework.stereotype.Component;

@Component(value = "c1") // value 로 컴포넌트 이름 설정 가능. 기본 값 : 클래스이름 카멜표기형(classC1)
public class ClassC1 implements ClassC {
    @Override
    public void classCallC() {
        System.out.println("ClassC1 호출");
    }
}
