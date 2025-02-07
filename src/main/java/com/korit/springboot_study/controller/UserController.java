package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.ReqAddUserDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.User;
import com.korit.springboot_study.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Validated
@RestController
@Api(tags = "사용자 정보 API")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/user/username/duplication")
    public ResponseEntity<SuccessResponseDto<Boolean>> duplicateUsername(
            @RequestParam
            @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$", message = "사용자 이름은 영문 대소문자, 숫자, 밑줄(_) 만 포함할 수 있습니다.(최소 4자 이상, 16자 이하)")
            String username) {

        return ResponseEntity.ok().body(new SuccessResponseDto<>(userService.duplicateUsername(username)));
    }

    @ApiOperation(value = "사용자 추가")
    @PostMapping("/api/user")
    public ResponseEntity<SuccessResponseDto<User>> addUser(@Valid @RequestBody ReqAddUserDto reqAddUserDto) throws MethodArgumentNotValidException {
        return ResponseEntity.ok().body(new SuccessResponseDto<>(userService.addUser(reqAddUserDto)));
    }
}
