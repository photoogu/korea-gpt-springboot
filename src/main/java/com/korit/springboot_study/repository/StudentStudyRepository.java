package com.korit.springboot_study.repository;

import com.korit.springboot_study.entity.study.Instructor;
import com.korit.springboot_study.entity.study.Major;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.mapper.StudentStudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class StudentStudyRepository {

    @Autowired
    private StudentStudyMapper studentStudyMapper;

    public Optional<List<Major>> findMajorAll() {
        List<Major> foundMajors = studentStudyMapper.selectMajorsAll();

        // List 는 내부 값이 비어있어도(selectMajorsAll 결과가 없어도) Optional 입장에서는 null 값이 아님. List 의 주소는 존재함
        // 따라서 아래와 같이 Optional.empty 로 return 하는 코드를 작성하는 것
//        if(foundMajors.isEmpty()) {
//            return Optional.empty();
//        }
//
//        return Optional.ofNullable(foundMajors);
        // .of() : 절대 null 이 아닐 경우 사용
        // .ofNullable() : null 인 경우 포함

        // 위의 코드를 줄이면 다음과 같다
        return foundMajors.isEmpty()
                ? Optional.empty()
                : Optional.ofNullable(foundMajors);
    }

    public Optional<List<Instructor>> findInstructorAll() {
        List<Instructor> foundInstructors = studentStudyMapper.selectInstructorsAll();

        return foundInstructors.isEmpty()
                ? Optional.empty()
                : Optional.ofNullable(foundInstructors);
    }

    public Optional<Major> saveMajor(Major major) throws DuplicateKeyException { // 중복인 경우 (major_name 에 Unique 를 걸어두었기 때문)
        try {
            studentStudyMapper.insertMajor(major);
        } catch (DuplicateKeyException e) {
            throw new CustomDuplicateKeyException(
                    e.getMessage(),
                    Map.of("majorName", "이미 존재하는 학과명입니다.")
            );
        }
        return Optional.ofNullable(new Major(major.getMajorId(), major.getMajorName()));
    }

    public Optional<Instructor> saveInstructor(Instructor instructor) throws DuplicateKeyException {
        try {
            studentStudyMapper.insertInstructor(instructor);
        } catch (DuplicateKeyException e) {
            throw new CustomDuplicateKeyException(
                    e.getMessage(),
                    Map.of("instructorName", "이미 존재하는 교수명입니다.")
            );
        }
        return Optional.ofNullable(new Instructor(instructor.getInstructorId(), instructor.getInstructorName()));
    }

}
