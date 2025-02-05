package com.korit.springboot_study.controller.advice;

import com.korit.springboot_study.dto.response.common.BadRequestResponseDto;
import com.korit.springboot_study.dto.response.common.NotFoundResponseDto;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<NotFoundResponseDto<?>> notFound(NotFoundException e) {
        return ResponseEntity.status(404).body(new NotFoundResponseDto<>(e.getMessage()));
    }

    @ExceptionHandler(value = CustomDuplicateKeyException.class)
    public ResponseEntity<BadRequestResponseDto<?>> duplicateKey(CustomDuplicateKeyException e) {
        return ResponseEntity.status(400).body(new BadRequestResponseDto<>(e.getErrors()));
    }
}
