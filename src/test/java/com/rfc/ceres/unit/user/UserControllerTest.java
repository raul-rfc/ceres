package com.rfc.ceres.unit.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rfc.ceres.common.PageResult;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("User Controller Tests")
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private UserService userService;

	@DisplayName("GET /users")
	@Test
	void whenFindAll_returnAllUsers() throws Exception {
		User marvin = new User();
		marvin.setName("Marvin");

		List<User> allUsers = new ArrayList<>();
		allUsers.add(marvin);

		Pageable pageable = PageRequest.of(0, 5);
		Page<User> page = new PageImpl<>(allUsers, pageable, 0);
		PageResult<User> pageResult = new PageResult<>(page);

		given(userService.findAll(pageable)).willReturn(pageResult);

		mockMvc.perform(get("/users")
				.param("page", "0")
				.param("size", "5"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content", hasSize(1)));
	}

	@DisplayName("GET /users/{id}")
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

	@DisplayName("POST /users")
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

	@DisplayName("PATCH /users/{id}")
	@Test
	void whenUpdateUser_returnUpdatedUser() throws Exception {
		User marvin = new User();
		marvin.setId(1L);
		marvin.setUserName("Marvellous");

		given(userService.update(any(User.class))).willReturn(marvin);

		mockMvc.perform(patch("/users/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(marvin)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userName", Matchers.is("Marvellous")));
	}

}
