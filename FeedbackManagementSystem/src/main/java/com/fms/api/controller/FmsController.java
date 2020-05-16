package com.fms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fms.api.model.User;
import com.fms.api.services.IFmsService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FmsController {

	@Autowired
	private IFmsService fmsService;

	public FmsController(IFmsService fmsService) {
		this.fmsService = fmsService;
	}

	@PostMapping("/create")
	public Mono<User> createUser(@RequestBody User user) {
		return fmsService.saveUser(user);
	}

	@GetMapping("/get/{userId}")
	public Mono<ResponseEntity<User>> getUserById(@PathVariable("userId") String userId) {
		return fmsService.getUserById(userId);
	}

	@GetMapping("/getAll")
	public Flux<User> getAllUser() {
		return fmsService.findAll();
	}

	@PutMapping("/update/{userId}")
	public Mono<ResponseEntity<User>> updateUser(@PathVariable("userId") String userId, @RequestBody User user) {
		return fmsService.updateUser(userId, user);
	}

	@DeleteMapping(value = "/delete/{userId}")
	public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String userId) {
		return fmsService.deleteUser(userId);
	}

}
