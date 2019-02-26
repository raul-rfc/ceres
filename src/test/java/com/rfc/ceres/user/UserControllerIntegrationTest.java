package com.rfc.ceres.user;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.TreeSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	public void whenFindAll_returnAllUsers() throws Exception {
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
	public void whenFindById_returnUser() throws Exception {
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
	public void whenCreateUser_returnLocation() throws Exception {
		User marvin = new User();
		marvin.setId(1L);
		marvin.setUserName("Marvellous");

		when(userService.create(any(User.class))).thenReturn(marvin);

		mockMvc.perform(post("/users"))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location",  "http://localhost/users/1"));
	}

	@Test
	public void whenUpdateUser_returnUpdatedUser() throws Exception {
		User marvin = new User();
		marvin.setId(1L);
		marvin.setUserName("Marvellous");

		when(userService.update(any(User.class), 1L)).thenReturn(marvin);

		mockMvc.perform(patch("/users/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(header().string("Location",  "http://localhost/users/1"));
	}

}
