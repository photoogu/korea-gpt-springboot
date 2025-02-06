package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.ReqAddPublisherDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Publisher;
import com.korit.springboot_study.repository.PublisherRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public SuccessResponseDto<List<Publisher>> getPublishersAll() throws NotFoundException {
        List<Publisher> foundPublishers = publisherRepository.findPublisherAll()
                .orElseThrow(() -> new NotFoundException("출판사 데이터가 존재하지 않습니다."));

        return new SuccessResponseDto<>(foundPublishers);
    }

    public SuccessResponseDto<List<Publisher>> getPublisherByName(String publisherName) throws NotFoundException {
        List<Publisher> foundPublishers = publisherRepository.findPublisherByName(publisherName)
                .orElseThrow(() -> new NotFoundException("출판사 데이터가 존재하지 않습니다."));

        return new SuccessResponseDto<>(foundPublishers);
    }

    public SuccessResponseDto<Publisher> savePublisher(ReqAddPublisherDto reqAddPublisherDto) throws DuplicateKeyException {
        return new SuccessResponseDto<>(
                publisherRepository
                        .savePublisher(new Publisher(0, reqAddPublisherDto.getPublisherName()))
                        .orElseThrow()
        );
    }
}
