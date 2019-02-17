package com.rfc.ceres.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.TreeSet;

@RestController
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public ResponseEntity<?> findAllUsers() {
		TreeSet<User> users = userService.findAll();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<?> findUser(@PathVariable("id") Long id) {
		Optional<User> user = userService.findById(id);
		return ResponseEntity.ok(user);
	}

	@PostMapping("/users")
	public ResponseEntity<?> createUser(User user) {
		User savedUser = userService.create(user);
		return ResponseEntity.ok(savedUser);
	}
}
