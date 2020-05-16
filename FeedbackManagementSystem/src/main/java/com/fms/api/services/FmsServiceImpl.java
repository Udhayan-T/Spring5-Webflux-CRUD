package com.fms.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fms.api.model.User;
import com.fms.api.repository.FmsRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FmsServiceImpl implements IFmsService {

	@Autowired
	private FmsRepository fmsRepository;

	public FmsServiceImpl(FmsRepository fmsRepository) {
		this.fmsRepository = fmsRepository;
	}

	@Override
	public Mono<User> saveUser(User user) {
		return fmsRepository.insert(user);
	}

	@Override
	public Mono<ResponseEntity<User>> getUserById(String userId) {
		return fmsRepository.findById(userId).map(ResponseEntity::ok)
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@Override
	public Flux<User> findAll() {
		return fmsRepository.findAll();
	}

	@Override
	public Mono<ResponseEntity<User>> updateUser(String userId, User user) {
		
		return fmsRepository.findById(userId).flatMap(currentUser -> {
			currentUser.setUserName(user.getUserName());
			currentUser.setUserPassword(user.getUserPassword());
			return fmsRepository.save(currentUser);
		})
		.map(updatedUser -> new ResponseEntity<User>(updatedUser, HttpStatus.OK))
		.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
	}

	@Override
	public Mono<ResponseEntity<Void>> deleteUser(String id) {
		return fmsRepository.deleteById(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
	}

}
