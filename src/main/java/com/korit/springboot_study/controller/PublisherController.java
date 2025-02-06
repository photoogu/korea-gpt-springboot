package com.korit.springboot_study.controller;

import com.korit.springboot_study.dto.request.ReqAddPublisherDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Publisher;
import com.korit.springboot_study.service.PublisherService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/api/publishers")
    @ApiOperation(value = "출판사 정보 조회")
    public ResponseEntity<SuccessResponseDto<List<Publisher>>> getPublishers(
            @RequestParam(required = false) String publisherName
    ) throws NotFoundException {
        SuccessResponseDto<List<Publisher>> foundPublishers = null;

        if (publisherName != null) {
            foundPublishers = publisherService.getPublisherByName(publisherName);
        } else {
            foundPublishers = publisherService.getPublishersAll();
        }

        return ResponseEntity.ok().body(foundPublishers);
    }

    @PostMapping("/api/publisher")
    @ApiOperation(value = "출판사 추가")
    public ResponseEntity<SuccessResponseDto<Publisher>> addAuthor(
            @RequestBody ReqAddPublisherDto reqAddPublisherDto
    ) {
        return ResponseEntity.ok().body(publisherService.savePublisher(reqAddPublisherDto));
    }

}
