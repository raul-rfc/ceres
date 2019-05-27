package com.rfc.ceres.unit.user;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
	public ResponseEntity<?> findAllUsers(@PageableDefault() Pageable pageable) {
		var users = userService.findAll(pageable);
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
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody() User user) {
		user.setId(id);
		var savedUser = userService.update(user);
		return ResponseEntity.ok(savedUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
