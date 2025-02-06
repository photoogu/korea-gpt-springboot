package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.study.Instructor;
import com.korit.springboot_study.entity.study.Major;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentStudyMapper {       // Mapper 는 Interface 로 만들어야함! + resources.mappers 에 xml 파일 같이 만들어야함!

    List<Major> selectMajorsAll(); // >> 전체조회 (mybatis 에서 List<> 로 자동 반환 해줌
    // Major selectMajors(); >> 단건조회
    List<Instructor> selectInstructorsAll();

    // 성공 횟수 반환 (mapper 에서 insert 는 무조건 int 값 반환)
    int insertMajor(Major major);
    int insertInstructor(Instructor instructor);

    int updateMajorName(Major major);
}
