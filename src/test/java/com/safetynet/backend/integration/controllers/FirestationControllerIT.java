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
import com.safetynet.backend.controllers.FirestationController;
import com.safetynet.backend.dao.FirestationDAO;
import com.safetynet.backend.entities.Firestation;

/**
 * 
 * @author tonys
 *
 */
@WebMvcTest(controllers = { FirestationController.class })
class FirestationControllerIT {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	private FirestationDAO firestationDao;

	@Test
	void testAddFirestation() throws Exception {
		// arrange
		Firestation firestation = new Firestation();
		when(firestationDao.save(firestation)).thenReturn(firestation);
		final String requestBody = new ObjectMapper().writeValueAsString(firestation);
		// act
		mockMvc.perform(
				post("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(status().is2xxSuccessful());
	}

	@Test
	void testUpdateFirestation() throws Exception {
		// arrange
		Firestation firestation = new Firestation();
		when(firestationDao.save(firestation)).thenReturn(firestation);
		final String requestBody = new ObjectMapper().writeValueAsString(firestation);
		// act
		mockMvc.perform(
				put("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(status().is2xxSuccessful());
	}

	@Test
	void testDeleteFirestation() throws Exception {
		// arrange
		Firestation firestation = new Firestation();
		when(firestationDao.delete(firestation)).thenReturn(true);
		final String requestBody = new ObjectMapper().writeValueAsString(firestation);
		// act
		mockMvc.perform(
				delete("/firestation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(status().is2xxSuccessful());
	}

}
