package com.rfc.ceres.user;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.TreeSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//TODO this class should do the unit testing of the controller. It should be another UserIntegrationTest to test the integration with TestRestTemplate
@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	void whenFindAll_returnAllUsers() throws Exception {
		User marvin = new User();
		marvin.setName("Marvin");
		marvin.setUserName("Marvellous");

		TreeSet<User> allUsers = new TreeSet<>();
		allUsers.add(marvin);

		given(userService.findAll()).willReturn(allUsers);

		mockMvc.perform(get("/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(allUsers.size())));
	}

	@Test
	void whenFindById_returnUser() throws Exception {
		User marvin = new User();
		marvin.setId(1L);
		marvin.setName("Marvin");
		marvin.setUserName("Marvellous");

		given(userService.findById(1L)).willReturn(Optional.of(marvin));

		mockMvc.perform(get("/users/{id}", 1L))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.is(1)))
				.andExpect(jsonPath("$.name", Matchers.is("Marvin")))
				.andExpect(jsonPath("$.userName", Matchers.is("Marvellous")));
	}

	@Test
	void whenCreateUser_returnLocation() throws Exception {
		User marvin = new User();
		marvin.setId(1L);
		marvin.setUserName("Marvellous");

		given(userService.create(any(User.class))).willReturn(marvin);

		mockMvc.perform(post("/users"))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location",  "http://localhost/users/1"));
	}

	@Test
	void whenUpdateUser_returnUpdatedUser() throws Exception {
		User marvin = new User();
		marvin.setId(1L);
		marvin.setUserName("Marvellous");

		given(userService.update(any(User.class), 1L)).willReturn(marvin);

		mockMvc.perform(patch("/users/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(header().string("Location",  "http://localhost/users/1"));
	}

}
