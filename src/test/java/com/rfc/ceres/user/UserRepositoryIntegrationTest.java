package com.rfc.ceres.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void whenFindByName_thenReturnUser() {
		// given
		User user = new User();
		user.setName("Marvin");
		user.setEmail("marvin@protonmail.com");
		user.setSurname("Langley");
		user.setSecondSurname("Williams");
		user.setUserName("Marvellous");
		User savedUser = entityManager.persist(user);
		entityManager.flush();

		// when
		Optional<User> found = userRepository.findById(savedUser.getId());

		// then
		assertThat(found.map(User::getName).isPresent());
	}

}
