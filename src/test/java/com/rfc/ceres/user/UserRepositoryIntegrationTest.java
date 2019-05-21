package com.rfc.ceres.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void whenFindByUserName_thenReturnUser() {
		// given
		User marvin = new User();
		marvin.setName("Marvin");
		marvin.setEmail("marvin@protonmail.com");
		marvin.setSurname("Langley");
		marvin.setSecondSurname("Williams");
		marvin.setUserName("Marvellous");
		User savedUser = entityManager.persist(marvin);
		entityManager.flush();

		// when
		Optional<User> foundUser = userRepository.findByUserName(savedUser.getUserName());

		// then
		assertTrue(foundUser.isPresent());
		foundUser.ifPresent(user -> assertEquals("Marvin", user.getName()));
		foundUser.ifPresent(user -> assertEquals("marvin@protonmail.com", user.getEmail()));
		foundUser.ifPresent(user -> assertEquals("Langley", user.getSurname()));
		foundUser.ifPresent(user -> assertEquals("Williams", user.getSecondSurname()));
		foundUser.ifPresent(user -> assertEquals("Marvellous", user.getUserName()));
	}

	@Test
	public void whenFindById_thenReturnUser() {
		// given
		User marvin = new User();
		marvin.setName("Marvin");
		marvin.setEmail("marvin@protonmail.com");
		marvin.setSurname("Langley");
		marvin.setSecondSurname("Williams");
		marvin.setUserName("Marvellous");
		User savedUser = entityManager.persist(marvin);
		entityManager.flush();

		// when
		Optional<User> foundUser = userRepository.findById(savedUser.getId());

		// then
		assertTrue(foundUser.isPresent());
		foundUser.ifPresent(user -> assertEquals("Marvin", user.getName()));
		foundUser.ifPresent(user -> assertEquals("marvin@protonmail.com", user.getEmail()));
		foundUser.ifPresent(user -> assertEquals("Langley", user.getSurname()));
		foundUser.ifPresent(user -> assertEquals("Williams", user.getSecondSurname()));
		foundUser.ifPresent(user -> assertEquals("Marvellous", user.getUserName()));
	}

	@Test
	public void whenExistsById_thenReturnTrue() {
		// given
		User marvin = new User();
		marvin.setName("Marvin");
		marvin.setEmail("marvin@protonmail.com");
		marvin.setSurname("Langley");
		marvin.setSecondSurname("Williams");
		marvin.setUserName("Marvellous");
		User savedUser = entityManager.persist(marvin);
		entityManager.flush();

		// when
		boolean userExists = userRepository.existsById(savedUser.getId());

		// then
		assertTrue(userExists);
	}

	@Test
	public void whenSave_thenReturnUser() {
		// given
		User marvin = new User();
		marvin.setName("Marvin");
		marvin.setEmail("marvin@protonmail.com");
		marvin.setSurname("Langley");
		marvin.setSecondSurname("Williams");
		marvin.setUserName("Marvellous");

		// when
		User savedUser = userRepository.save(marvin);

		// then
		assertEquals("Marvin", savedUser.getName());
		assertEquals("marvin@protonmail.com", savedUser.getEmail());
		assertEquals("Langley", savedUser.getSurname());
		assertEquals("Williams", savedUser.getSecondSurname());
		assertEquals("Marvellous", savedUser.getUserName());
	}

	@Test
	public void whenSaveAll_thenReturnAllUsers() {
		// given
		User marvin = new User();
		marvin.setName("Marvin");
		marvin.setEmail("marvin@protonmail.com");
		marvin.setSurname("Langley");
		marvin.setSecondSurname("Williams");
		marvin.setUserName("Marvellous");

		User marvin2 = new User();
		marvin2.setName("Marvin2");
		marvin2.setEmail("marvin2@protonmail.com");
		marvin2.setSurname("Langley2");
		marvin2.setSecondSurname("Williams2");
		marvin2.setUserName("Marvellous2");

		var userList = List.of(marvin, marvin2);

		// when
		List<User> savedUsers = (List<User>) userRepository.saveAll(userList);

		// then
		assertFalse(savedUsers.isEmpty());
		assertEquals(2, savedUsers.size());
	}



}
