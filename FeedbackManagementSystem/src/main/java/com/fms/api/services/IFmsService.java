package com.fms.api.services;

import org.springframework.http.ResponseEntity;

import com.fms.api.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFmsService {
	
	Mono<User> saveUser(User user);
    
	Mono<ResponseEntity<User>> getUserById(String userId);
 
    Flux<User> findAll();
 
    Mono<ResponseEntity<User>> updateUser(String userId, User user);
 
    Mono<ResponseEntity<Void>> deleteUser(String userId);

}
