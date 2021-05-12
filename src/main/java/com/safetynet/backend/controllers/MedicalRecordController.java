/**
 * 
 */
package com.safetynet.backend.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.backend.dao.facade.FacadeMEdicalRecordDAO;
import com.safetynet.backend.entities.MedicalRecord;

/**
 * @author tonys
 *
 */
@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

	private final FacadeMEdicalRecordDAO medicalRecordDao;
	
	private static final Logger LOG = LogManager.getLogger();
	
	public MedicalRecordController(FacadeMEdicalRecordDAO dao) {
		this.medicalRecordDao = dao;
	}
	
	@PostMapping
	public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord mr) {
		LOG.debug("[ POST /medicalRecord ]");
		medicalRecordDao.save(mr);
		LOG.info("MedicalRecord created - {}",mr);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord mr) {
		LOG.debug("[ PUT /medicalRecord ]");
		medicalRecordDao.save(mr);
		LOG.info("MedicalRecord updated - {}",mr);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<MedicalRecord> deleteMedicalRecord(@RequestBody MedicalRecord mr) {
		LOG.debug("[ DELETE /medicalRecord ]");
		medicalRecordDao.delete(mr);
		LOG.info("MedicalRecord deleted - {}",mr);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
