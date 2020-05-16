package com.fms.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.fms.api.model.User;

@Repository
public interface FmsRepository extends ReactiveMongoRepository<User, String> {

}
