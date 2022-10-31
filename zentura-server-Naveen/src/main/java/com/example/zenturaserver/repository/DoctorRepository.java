package com.example.zenturaserver.repository;

import com.example.zenturaserver.model.DoctorDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends MongoRepository<DoctorDTO,String> {
}
