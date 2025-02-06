package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.ReqAddAuthorDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.Author;
import com.korit.springboot_study.repository.AuthorRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public SuccessResponseDto<List<Author>> getAuthorsAll() throws NotFoundException {
        List<Author> foundAuthors = authorRepository.findAuthorAll()
                .orElseThrow(() -> new NotFoundException("저자 데이터가 존재하지 않습니다."));

        return new SuccessResponseDto<>(foundAuthors);
    }

    public SuccessResponseDto<List<Author>> getAuthorByName(String authorName) throws NotFoundException {
        List<Author> foundAuthors = authorRepository.findAuthorByName(authorName)
                .orElseThrow(() -> new NotFoundException("저자 데이터가 존재하지 않습니다."));

        return new SuccessResponseDto<>(foundAuthors);
    }

    public SuccessResponseDto<Author> saveAuthor(ReqAddAuthorDto reqAddAuthorDto) throws DuplicateKeyException {
        return new SuccessResponseDto<>(
                authorRepository
                        .saveAuthor(new Author(0, reqAddAuthorDto.getAuthorName()))
                        .orElseThrow()
        );
    }
}
