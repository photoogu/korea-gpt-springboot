package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.study.ReqAddInstructorDto;
import com.korit.springboot_study.dto.request.study.ReqAddMajorDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Instructor;
import com.korit.springboot_study.entity.study.Major;
import com.korit.springboot_study.service.StudentStudyService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentStudyController {

    @Autowired
    private StudentStudyService studentStudyService;

    @GetMapping("/api/study/majors")
    @ApiOperation(value = "학과 전체 조회")
    public ResponseEntity<SuccessResponseDto<List<Major>>> getMajors() throws NotFoundException {
        return ResponseEntity.ok().body(studentStudyService.getMajorsAll());
    }

    @GetMapping("/api/study/instructors")
    @ApiOperation(value = "교수 정보 전체 조회")
    public ResponseEntity<SuccessResponseDto<List<Instructor>>> getInstructor() throws NotFoundException {
        return ResponseEntity.ok().body(studentStudyService.getInstructorsAll());
    }

    @PostMapping("/api/study/major")
    public ResponseEntity<SuccessResponseDto<?>> addMajor(@RequestBody ReqAddMajorDto reqAddMajorDto) {
        System.out.println(reqAddMajorDto);
        return ResponseEntity.ok().body(studentStudyService.addMajor(reqAddMajorDto));
    }

    @PostMapping("/api/study/instructor")
    public ResponseEntity<SuccessResponseDto<?>> addInstructor(@RequestBody ReqAddInstructorDto reqAddInstructorDto) {
        System.out.println(reqAddInstructorDto);
        return ResponseEntity.ok().body(studentStudyService.addInstructor(reqAddInstructorDto));
    }

}

/*
* 학과 조회 전체적인 흐름
* - controller 로 요청이 들어옴
* - Service 에서 SuccessResponseDto 를 리턴해야함
* - Service 에서 StudentStudyRepository 의 findMajorAll 를 호출하여 List 로 데이터를 담음
*       - orElseThrow 로 예외처리를 통해 바로 예외 발생시켜줌(throws)
*           >> 원래는 try-catch 문을 사용해야함.
*              but GlobalRestControllerAdvice 에서 ExceptionHandler 로 예외처리가 되는지 확인하여
*              예외(NotFoundException)가 발생할 시 가져와서 처리해줌(return ResponseEntity.status(404).body(new NotFoundResponseDto<>(e.getMessage()));
*           >> ControllerAdvice 는 Controller 에서 모든 요청에 대해 발생할 수 있는 예외, 즉 실패에 대한 응답을 처리해준다.
*           >> Advice 가 없을 시, 모든 요청에 대해 예외처리를 각각 해줘야함.
*           >> Advice 로 예외를 처리할 시, try-catch 하면 안됨.
*               >> catch 로 예외를 처리하게 되면 코드가 정상 작동을 함. 즉, null 인 상태로 SuccessResponseDto<>(null) 이 리턴되어 advice 가 아닌 controller 로 전달됨.
*               >> 이는 Success 로 처리가 됨. (data: null, status: 200, message: "요청이 성공적으로 처리가 되었습니다." >> 이렇게 출력됨)
* */

