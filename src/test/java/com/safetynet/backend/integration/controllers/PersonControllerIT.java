package com.safetynet.backend.integration.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.backend.controllers.PersonController;
import com.safetynet.backend.dao.PersonDAO;
import com.safetynet.backend.entities.Person;

/**
 * 
 * @author tonys
 *
 */
@WebMvcTest(controllers = { PersonController.class })
class PersonControllerIT {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	private PersonDAO personDao;

	@Test
	void testAddPerson() throws Exception {
		// arrange
		Person person = new Person();
		when(personDao.save(person)).thenReturn(person);
		final String requestBody = new ObjectMapper().writeValueAsString(person);
		// act
		mockMvc.perform(
				post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(status().is2xxSuccessful());
	}

	@Test
	void testUpdatePerson() throws Exception {
		// arrange
		Person person = new Person();
		when(personDao.save(person)).thenReturn(person);
		final String requestBody = new ObjectMapper().writeValueAsString(person);
		// act
		mockMvc.perform(
				put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(status().is2xxSuccessful());
	}

	@Test
	void testDeletePerson() throws Exception {
		// arrange
		Person person = new Person();
		when(personDao.delete(person)).thenReturn(true);
		final String requestBody = new ObjectMapper().writeValueAsString(person);
		// act
		mockMvc.perform(
				delete("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(status().is2xxSuccessful());
	}

}
