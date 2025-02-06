package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.ReqAddAuthorDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Author;
import com.korit.springboot_study.service.AuthorService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/api/authors")
    @ApiOperation(value = "저자 정보 조회")
    public ResponseEntity<SuccessResponseDto<List<Author>>> getAuthors(
            @RequestParam(required = false) String authorName
            ) throws NotFoundException {
        SuccessResponseDto<List<Author>> foundAuthors = null;

        if (authorName != null) {
            foundAuthors = authorService.getAuthorByName(authorName);
        } else {
            foundAuthors = authorService.getAuthorsAll();
        }

        return ResponseEntity.ok().body(foundAuthors);
    }

    @PostMapping("/api/author")
    @ApiOperation(value = "저자 추가")
    public ResponseEntity<SuccessResponseDto<Author>> addAuthor(
            @RequestBody ReqAddAuthorDto reqAddAuthorDto
    ) {
        return ResponseEntity.ok().body(authorService.saveAuthor(reqAddAuthorDto));
    }

}
