package com.rfc.ceres.unit.user;

import com.rfc.ceres.common.MyBeanUtils;
import com.rfc.ceres.common.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public PageResult<User> findAll(Pageable pageable) {
		return new PageResult<>(userRepository.findAll(pageable));
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public Optional<User> findByUserName(String username) {
		return userRepository.findByUserName(username);
	}

	public User create(User user) {
		return userRepository.save(user);
	}

	@Transactional
	public User update(User user) {
		User savedUser = userRepository.findById(user.getId()).orElseThrow(EntityNotFoundException::new);
		MyBeanUtils.copyNotNullProperties(user, savedUser, Collections.singletonList("secondName"));
		return userRepository.save(savedUser);
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}
}
