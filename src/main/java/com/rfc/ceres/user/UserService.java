package com.rfc.ceres.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.TreeSet;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public TreeSet<User> findAll() {
		Comparator<User> userComparator = Comparator.comparing(User::getName);
		TreeSet<User> users = new TreeSet<>(userComparator);
		users.addAll((Collection<? extends User>) userRepository.findAll());
		return users;
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public User create(User user) {
		return userRepository.save(user);
	}

	public User update(User user, Long id) {
		user.setId(id);
		return userRepository.save(user);
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}
}
