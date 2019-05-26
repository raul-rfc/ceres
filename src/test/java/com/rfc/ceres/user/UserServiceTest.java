package com.rfc.ceres.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setUp() {
		User marvin = new User();
		marvin.setUserName("Marvellous");
		Mockito.when(userRepository.findByUserName(marvin.getUserName())).thenReturn(Optional.of(marvin));
	}

	@DisplayName("Find by username")
	@Test
	void testFindByUsername() {
		String username = "Marvellous";
		Optional<User> foundUser = userService.findByUserName(username);
		foundUser.ifPresent(user -> assertEquals("Marvellous", user.getUserName()));
	}
}
