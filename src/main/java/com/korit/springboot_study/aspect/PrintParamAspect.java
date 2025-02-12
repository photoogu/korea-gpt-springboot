package com.korit.springboot_study.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PrintParamAspect {     // 리턴 잘 되었는가 print 하여 확인하는 메서드 정의

    private static final Logger log = LoggerFactory.getLogger(PrintParamAspect.class);

    @Pointcut("@annotation(com.korit.springboot_study.aspect.annotation.PrintParamsAop)")
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        // Signature 은 변수 이름은 가져올 수 없음
        // >> Signature 을 CodeSignature 로 다운캐스팅하면 getParameterNames 를 사용하여 변수 이름을 가져올 수 있다
        String[] parameterNames = codeSignature.getParameterNames();
        Object[] args = joinPoint.getArgs(); // 입력한 변수 값 가져오기!
//        log.info("로그를 출력합니다.");
        log.error("로그를 출력합니다.");
        // System.out.println() > 간단한 출력용
        // log.info() > 꼭 필요한 것들 출력용(입력값 정보, 요청 ip 정보, error 정보 등)

        for (int i = 0; i < parameterNames.length; i++) { // parameterNames 와 args 의 배열 길이가 같으므로 같은 i 를 사용할 수 있다
            System.out.println(parameterNames[i] + ": " + args[i]); // 입력한 변수 이름 + 변수 값 출력!
        }

        Object result = joinPoint.proceed();

        return result;
    }

}
