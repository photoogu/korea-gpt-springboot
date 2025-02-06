package com.korit.springboot_study.repository;

import com.korit.springboot_study.entity.Author;
import com.korit.springboot_study.entity.Publisher;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.mapper.AuthorMapper;
import com.korit.springboot_study.mapper.PublisherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PublisherRepository {

    @Autowired
    private PublisherMapper publisherMapper;

    public Optional<List<Publisher>> findPublisherAll() {
        List<Publisher> foundPublishers = publisherMapper.selectPublisherAll();

        return foundPublishers.isEmpty()
                ? Optional.empty()
                : Optional.ofNullable(foundPublishers);
    }

    public Optional<List<Publisher>> findPublisherByName(String publisherName) {
        List<Publisher> foundPublishers = publisherMapper.selectPublisherByName(publisherName);

        return foundPublishers.isEmpty()
                ? Optional.empty()
                : Optional.ofNullable(foundPublishers);
    }

    public Optional<Publisher> savePublisher(Publisher publisher) throws DuplicateKeyException {
        try {
            publisherMapper.insertPublisher(publisher);
        } catch (DuplicateKeyException e) {
            throw new CustomDuplicateKeyException(e.getMessage(), Map.of("publisherName", "이미 존재하는 출판사 입니다."));
        }
        return Optional.ofNullable(new Publisher(publisher.getPublisherId(), publisher.getPublisherName()));
    }

}
