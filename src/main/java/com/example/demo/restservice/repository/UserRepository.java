package com.example.demo.restservice.repository;

import com.example.demo.restservice.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ElasticsearchRepository<UserDocument, String> {

}
