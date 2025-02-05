package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.study.ReqAddInstructorDto;
import com.korit.springboot_study.dto.request.study.ReqAddMajorDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Instructor;
import com.korit.springboot_study.entity.study.Major;
import com.korit.springboot_study.repository.StudentStudyRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentStudyService {

    @Autowired
    private StudentStudyRepository studentStudyRepository;

    public SuccessResponseDto<List<Major>> getMajorsAll() throws NotFoundException {
        List<Major> foundMajors = studentStudyRepository.findMajorAll()
                .orElseThrow(() -> new NotFoundException("학과 데이터가 존재하지 않습니다."));

        return new SuccessResponseDto<>(foundMajors);
    }

    public SuccessResponseDto<List<Instructor>> getInstructorsAll() throws NotFoundException {
        List<Instructor> foundInstructors = studentStudyRepository.findInstructorAll()
                .orElseThrow(() -> new NotFoundException("교수 정보 데이터가 존재하지 않습니다."));

        return new SuccessResponseDto<>(foundInstructors);
    }

    // insert 중에 오류가 발생하는건 굉장히 위험하다. 따라서 완충역할인 Transactional 이 필요!
    @Transactional(rollbackFor = Exception.class)
    public SuccessResponseDto<Major> addMajor(ReqAddMajorDto reqAddMajorDto) throws DuplicateKeyException {
        return new SuccessResponseDto<>(
                studentStudyRepository
                        .saveMajor(new Major(0, reqAddMajorDto.getMajorName()))
                        .orElseThrow()
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public SuccessResponseDto<Instructor> addInstructor(ReqAddInstructorDto reqAddInstructorDto) throws DuplicateKeyException {
        return new SuccessResponseDto<>(
                studentStudyRepository
                        .saveInstructor(new Instructor(0, reqAddInstructorDto.getInstructorName()))
                        .orElseThrow()
        );
    }

}
