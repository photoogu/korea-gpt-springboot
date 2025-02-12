package com.korit.springboot_study.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAspect {

    // 모든 리턴타입 + PostService 클래스 + getPostById 메서드 + 모든 매개변수
    @Pointcut("execution(* com.korit.springboot_study.service.PostService.getPostById(..))")
    private void executionPointCut() {}

    // 해당 어노테이션이 달려있는 위치로 pointcut 지정
    @Pointcut("@annotation(com.korit.springboot_study.aspect.annotation.TimerAop)")
    private void annotationPointCut() {}

//    @Around("pointCut()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("전처리");
//        Object result = joinPoint.proceed(); // getPostById 호출!
//        System.out.println("후처리");
//        return result;
//    }
    // Object 타입으로 명시하여도, @Aspect 어노테이션이 달려있어 aop 라이브러리에서 자동으로 타입 지정을 해줌
    // joinPoint 는 핵심로직!
    // proceed() 는 리턴타입이 Object >> @Around 의 메서드 리턴타입을 Object 로 함
    // >> @Aspect 가 있으면 aop 라이브러리에서 Service 객체(PostService)를 만들 때 코드를 분석하여 @Around 메서드가 Service 의 메서드와 합쳐져 다시 만들어지게 됨(재설계).
    // 이 과정에서 Object 타입을 자동으로 Controller 에서 사용할 수 있게 만들어줌(이때, return 이 있어야함!)
    // >> 따라서 Object 로 둬도 Controller 에서 다운캐스팅 없이 바로 메서드 호출 가능!

    // @Around("execution(* com.korit.springboot_study.service.PostService.getPostById(..))||@annotation(com.korit.springboot_study.aspect.annotation.TimerAop)")
    // 이를 변수화 하여 메서드로 만든 것이 위의 executionPointCut, annotationPointCut 메서드임!
    @Around("executionPointCut()||annotationPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        System.out.println("메소드 실행시간: " + stopWatch.getTotalTimeSeconds());
        return result;
    }

}
