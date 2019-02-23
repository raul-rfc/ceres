package com.rfc.ceres.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceIntegrationTest {

	@TestConfiguration
	static class UserServiceTestContextConfiguration {

		@Autowired
		UserRepository repository;

		@Bean
		public UserService userService() {
			return new UserService(repository);
		}
	}

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Before
	public void setUp() {
		User marvin = new User();
		marvin.setUserName("Marvellous");

		Mockito.when(userRepository.findByUserName(marvin.getUserName())).thenReturn(Optional.of(marvin));
	}

	@Test
	public void whenValidUserName_thenUserShouldBeFound() {
		String username = "Marvellous";
		User found = userService.findByUserName(username).get();

		assertThat(found.getUserName()).isEqualTo(username);
	}
}
