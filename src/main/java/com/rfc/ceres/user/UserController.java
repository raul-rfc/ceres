package com.rfc.ceres.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping()
	public ResponseEntity<?> findAllUsers() {
		var users = userService.findAll();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findUser(@PathVariable("id") Long id) {
		var user = userService.findById(id);
		return ResponseEntity.ok(user);
	}

	@PostMapping()
	public ResponseEntity<?> createUser(User user, UriComponentsBuilder uriBuilder) {
		var savedUser = userService.create(user);
		var userId = savedUser.getId();
		UriComponents uriComponents = uriBuilder.path("/users/{id}").buildAndExpand(userId);
		return ResponseEntity.created(uriComponents.toUri()).build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody() User user) {
		var savedUser = userService.update(user, id);
		return ResponseEntity.ok(savedUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
