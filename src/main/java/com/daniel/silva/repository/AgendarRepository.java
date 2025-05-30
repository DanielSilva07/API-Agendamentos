package com.daniel.silva.repository;

import com.daniel.silva.domain.model.AgendarModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendarRepository extends MongoRepository<AgendarModel,String> {
}
