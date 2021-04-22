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
import com.safetynet.backend.controllers.MedicalRecordController;
import com.safetynet.backend.dao.MedicalRecordDAO;
import com.safetynet.backend.entities.MedicalRecord;

/**
 * 
 * @author tonys
 *
 */
@WebMvcTest(controllers = { MedicalRecordController.class })
class MedicalRecordControllerIT {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	private MedicalRecordDAO medicalRecordDao;

	@Test
	void testAddMedicalRecord() throws Exception {
		// arrange
		MedicalRecord medicalRecord = new MedicalRecord();
		when(medicalRecordDao.save(medicalRecord)).thenReturn(medicalRecord);
		final String requestBody = new ObjectMapper().writeValueAsString(medicalRecord);
		// act
		mockMvc.perform(
				post("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(status().is2xxSuccessful());
	}

	@Test
	void testUpdateMedicalRecord() throws Exception {
		// arrange
		MedicalRecord medicalRecord = new MedicalRecord();
		when(medicalRecordDao.save(medicalRecord)).thenReturn(medicalRecord);
		final String requestBody = new ObjectMapper().writeValueAsString(medicalRecord);
		// act
		mockMvc.perform(
				put("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(status().is2xxSuccessful());
	}

	@Test
	void testDeleteMedicalRecord() throws Exception {
		// arrange
		MedicalRecord medicalRecord = new MedicalRecord();
		when(medicalRecordDao.delete(medicalRecord)).thenReturn(true);
		final String requestBody = new ObjectMapper().writeValueAsString(medicalRecord);
		// act
		mockMvc.perform(
				delete("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(status().is2xxSuccessful());
	}

}
