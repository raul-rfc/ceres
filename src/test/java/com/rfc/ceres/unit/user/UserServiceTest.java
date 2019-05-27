package com.rfc.ceres.unit.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("User Service Tests")
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setUp(){
		MockitoAnnotations.initMocks(this);
	}


	@Test
	@DisplayName("Find By Username")
	void testFindByUsername() {
		//Given
		String username = "Marvellous";
		User marvin = new User();
		marvin.setUserName(username);
		when(userRepository.findByUserName(marvin.getUserName())).thenReturn(Optional.of(marvin));

		//When
		Optional<User> foundUser = userService.findByUserName(username);

		//Then
		foundUser.ifPresent(user -> assertEquals("Marvellous", user.getUserName()));
		verify(userRepository,times(1)).findByUserName(username);
	}
}
