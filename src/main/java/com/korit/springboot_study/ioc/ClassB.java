package com.korit.springboot_study.ioc;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ClassB {

//    @Qualifier(value = "classC2")
//    @Autowired(required = false) // private final ClassC c + @RequiredArgsConstructor 대신 사용 // required = false >> final 키워드 뺀 것과 같음. true 가 기본값
//    private ClassC c;
//    // ClassC1, C2 둘 다 컴포넌트이므로, 구분하기 위해 컴포넌트 이름을 명확히 기재하는 것이 중요 (ClassC c >> classC1인지 classC2인지 구분X  -->  오류)
//    // @Qualifier 로 컴포넌트 이름 기재 가능

    @Qualifier("c1")
    @Autowired
    private ClassC c1;
    @Qualifier("c2")
    @Autowired
    private ClassC c2;

    public void classCallB() {
        System.out.println("ClassB 메소드 호출");
    }
}
