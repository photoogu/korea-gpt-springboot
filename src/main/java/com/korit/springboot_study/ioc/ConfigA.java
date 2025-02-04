package com.korit.springboot_study.ioc;

import org.springframework.asm.ByteVector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 우선 순위가 가장 높다 (설정 객체이기 때문) 즉, 실행과 동시에 Bean 등록이 된다.
public class ConfigA {

    // 메소드를 호출하지 않아도 자동으로 실행된다. 리턴객체를 IoC에 Bean 으로 등록한다. >> Configuration 에서!!
    @Bean(value = "aaa") // 메소드의 이름이 컴포넌트의 이름으로 등록되는 것이 기본값, 하지만 value 로 컴포넌트의 이름을 직접 설정 가능
    public ClassD call() {
        System.out.println("ConfigA call");
        return new ClassD();
    }

    @Bean
    public ByteVector byteVector() {
        return new ByteVector();
    }
    /*
    * ClassD 를 굳이 왜 이렇게 Configuration 으로 따로 Bean 등록을 해주어야하는가?
    * 1. 해당 class 의 생성자를 통해 매개변수를 다르게 주고 싶을 때
    * 2. 라이브러리를 Bean 으로 등록하고 싶을 때
    * */

}
