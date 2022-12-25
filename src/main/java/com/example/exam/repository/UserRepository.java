package com.example.exam.repository;

import com.example.exam.entity.Tariff;
import com.example.exam.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// репозиторій для роботи з базою даних для сутності User
// в даному випадку на прикладі MongoRepository
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findById(Integer id);
}
