package com.rfc.ceres.integration.user;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
class UserIntegrationTest {

	@Autowired
	private TestRestTemplate template;

	@LocalServerPort
	int port;


	@Test
	void testFindAllUsers() throws URISyntaxException
	{
		final String baseUrl = "http://localhost:"+port+"/v1/users/";
		URI uri = new URI(baseUrl);

		ResponseEntity<TreeSet> result = template.getForEntity(uri, TreeSet.class);

		//Verify request succeed
		assertEquals(200, result.getStatusCodeValue());
	}

}
