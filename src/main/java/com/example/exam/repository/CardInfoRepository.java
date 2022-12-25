package com.example.exam.repository;

import com.example.exam.entity.CardInfo;
import com.example.exam.entity.Tariff;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// репозиторій для роботи з базою даних для сутності CardInfo
// в даному випадку на прикладі MongoRepository
@Repository
public interface CardInfoRepository  extends MongoRepository<CardInfo, String> {
    CardInfo findById(Integer id);
}
