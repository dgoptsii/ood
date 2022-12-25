package com.example.exam.repository;

import com.example.exam.entity.User;
import com.example.exam.entity.UserSubscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// репозиторій для роботи з базою даних для сутності UserSubscription
// в даному випадку на прикладі MongoRepository
@Repository
public interface UserSubscriptionRepository extends MongoRepository<UserSubscription, String> {
    UserSubscription findById(Integer id);
}
