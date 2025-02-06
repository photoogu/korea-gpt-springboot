package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.Publisher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublisherMapper {
    List<Publisher> selectPublisherAll();
    List<Publisher> selectPublisherByName(String publisherName);
    int insertPublisher(Publisher publisher);

}
