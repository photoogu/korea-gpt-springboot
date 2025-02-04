package com.korit.springboot_study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/mvc/hello")
    public String hello(Model model) {
        model.addAttribute("name", "김준일");
        System.out.println("hello 메소드 호출");
        return "hello"; // view 파일 경로
        // return "resources/templates/hello.html";
        // thymeleaf prefix = resources/templates
        // thymeleaf suffix = .html
        // 위를 이어주는 것: ViewResolver
    }

    @GetMapping("/mvc/hello2")
    public String hello2() {
        System.out.println("hello 메소드 호출");
        return "hello";
    }
}
