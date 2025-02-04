package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.study.ReqAddStudentDto;
import com.korit.springboot_study.dto.request.study.ReqStudentDto;
import com.korit.springboot_study.dto.response.study.RespAddStudentDto;
import com.korit.springboot_study.dto.response.study.RespStudentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "REST API 수업") // 해당 컨트롤러의 이름을 바꾸는 등 설명 추가
public class FirstRestController {

    // @ResponseBody >> RestController 어노테이션을 통해 가능!
    // 응답 타입이 "객체" 인 경우, key-value 값을 JSON 으로 자동 변환(jackson 라이브러리)하여 응답해줌
    @GetMapping("/api/hello")
    public Map<String, Object> hello(HttpServletRequest request) {
        String age = request.getParameter("age");
        String address = request.getParameter("address");

        System.out.println(age);
        System.out.println(address);

        return Map.of("name", "김영경");
    }

    @GetMapping("/api/hello2")
    public Map<String, Object> hello2(@RequestParam int age, @RequestParam String address) {

        System.out.println(age);
        System.out.println(address);

        return Map.of("name", "김영경");
    }

    // /api/student/1
    @ApiOperation(value = "학생 조회(일반 for 선형탐색)", notes = "일반 for 문을 사용하여 선형 탐색 학습") // 메서드 설명 추가
    @GetMapping("/api/student")
    public Map<String, Object> getStudent(
            @ApiParam(value = "조회할 학생 인덱스", required = false)
            @RequestParam(required = false) int id
    ) {
        // 변수명과 key 값이 다를 경우, @RequestParam(name="studentId") int id 처럼 명시해줘야함
        List<Map<String, Object>> students = new ArrayList<>();
        students.add(Map.of("id", 11, "name", "최석현", "age", 26));
        students.add(Map.of("id", 22, "name", "백진우", "age", 32));
        students.add(Map.of("id", 33, "name", "이주원", "age", 28));
        students.add(Map.of("id", 44, "name", "정영훈", "age", 26));

        int studentId = -1;
        for(int i = 0; i < students.size(); i++) {
            //if(students.get(i).get("id").equals(id)) {  >> 내 풀이
            if((Integer)students.get(i).get("id") == id) { // value 값의 타입이 Object 이므로, Integer 로 다운캐스팅!
                studentId = i;
                break;
            }
        }
        if(studentId == -1) {
            return Map.of("error", "찾지 못했음");
        }

        return students.get(studentId);
    }

    @ApiOperation(value = "학생 조회(향상된 for 선형탐색)", notes = "향상된 for 문을 사용하여 선형 탐색 학습")
    @GetMapping("/api/student2")
    public Map<String, Object> getStudent2(
            @ApiParam(value = "조회할 학생 인덱스", required = false)
            @RequestParam(required = false) int id
    ) {
        List<Map<String, Object>> students = new ArrayList<>();
        students.add(Map.of("id", 11, "name", "최석현", "age", 26));
        students.add(Map.of("id", 22, "name", "백진우", "age", 32));
        students.add(Map.of("id", 33, "name", "이주원", "age", 28));
        students.add(Map.of("id", 44, "name", "정영훈", "age", 26));

        Map<String, Object> foundStudent = null;

        for(Map<String, Object> student : students) {
            if((Integer)student.get("id") == id) {
                foundStudent = student;
                break;
            }
        }
        if(foundStudent == null) {
            return Map.of("error", "찾지 못했음");
        }

        return foundStudent;
    }

    @ApiOperation(value = "학생 조회(stream 선형탐색)", notes = "stream 을 사용하여 선형 탐색 학습")
    @GetMapping("/api/student3")
    public Map<String, Object> getStudent3(
            @ApiParam(value = "조회할 학생 인덱스", required = false)
            @RequestParam(required = false) int id
    ) {
        List<Map<String, Object>> students = new ArrayList<>();
        students.add(Map.of("id", 11, "name", "최석현", "age", 26));
        students.add(Map.of("id", 22, "name", "백진우", "age", 32));
        students.add(Map.of("id", 33, "name", "이주원", "age", 28));
        students.add(Map.of("id", 44, "name", "정영훈", "age", 26));

        Map<String, Object> responseData = students.stream()
                .filter(student -> (Integer)(student.get("id")) == id)
                .findFirst().orElse(Map.of("error", "찾지 못했음"));
        // orElse: findFirst(Optional 타입) 로 찾은게 있으면 찾은 데이터 반환, 없으면 괄호 안의 데이터 반환
        return responseData;
    }

    @ApiOperation(value = "학생 조회(매개변수 여러개 + 경로변수)", notes = "매개변수가 여러개인 경우 + 경로 변수 적용(단건조회)")
    @GetMapping("/api/student4/{studentId}")
    public RespStudentDto getStudent4(
            @ApiParam(value = "학생 ID", required = true)
            @PathVariable int studentId,  // path variable: 경로의 변수 >> api/student4/1?name=김영경&age=26 과 같이 나옴

            @ModelAttribute
            ReqStudentDto reqStudentDto) {

        return new RespStudentDto(100, "김영경", 26);
    }

    @PostMapping("/api/student")
    @ApiOperation(value = "학생 추가(POST)", notes = "학생 정보를 입력받아 user_tb에 데이터를 저장합니다.")
    public ResponseEntity<RespAddStudentDto> addStudent(@RequestBody ReqAddStudentDto reqAddStudentDto) {
        System.out.println(reqAddStudentDto);
        return ResponseEntity.badRequest().body(new RespAddStudentDto("학생 추가 실패", false));
    }

    @PutMapping("/api/student/{studentId}")
    @ApiOperation(value = "학생 정보 수정(PUT)", notes = "학생 ID를 기준으로 학생 정보를 수정합니다.")
    public ResponseEntity<?> updateStudent(
            @ApiParam(value = "학생 ID", example = "1", required = true)
            @PathVariable int studentId,
            @RequestBody Map<String, Object> reqBody) {

        System.out.println(reqBody);

        return ResponseEntity.ok().body(null);
    }

    @ApiOperation(value = "학생 정보 삭제(DELETE)", notes = "학생 ID를 기준으로 정보를 삭제합니다")
    @DeleteMapping("/api/student/{studentId}")
    public ResponseEntity<?> deleteStudent(
            @ApiParam(value = "학생 ID", example = "1", required = true)
            @PathVariable int studentId) {

        return ResponseEntity.ok().body(null);
    }
    /*
     * GET: parameter 로 요청
     * POST, PUT: JSON 로 요청
     *
     * DTO 사용 or Map<String, Object> 사용 (DTO 로 사용하는 것을 권장)
     * */
}
