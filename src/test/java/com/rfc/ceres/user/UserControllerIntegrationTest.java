package com.rfc.ceres.user;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.TreeSet;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

}
