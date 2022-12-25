package com.example.exam.repository;

import com.example.exam.entity.CardInfo;
import com.example.exam.entity.Tariff;
import com.example.exam.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// репозиторій для роботи з базою даних для сутності Tariff
// в даному випадку на прикладі MongoRepository
@Repository
public interface TariffRepository  extends MongoRepository<Tariff, String> {
    Tariff findById(Integer id);
}
