package com.example.mongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.mongodb.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
