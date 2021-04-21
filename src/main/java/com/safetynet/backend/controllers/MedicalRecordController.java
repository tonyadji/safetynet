/**
 * 
 */
package com.safetynet.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.backend.dao.MedicalRecordDAO;
import com.safetynet.backend.entities.MedicalRecord;

/**
 * @author tonys
 *
 */
@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

	private final MedicalRecordDAO medicalRecordDao;
	
	public MedicalRecordController(MedicalRecordDAO dao) {
		this.medicalRecordDao = dao;
	}
	
	@PostMapping
	public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord mr) {
		medicalRecordDao.save(mr);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord mr) {
		medicalRecordDao.save(mr);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<MedicalRecord> deleteMedicalRecord(@RequestBody MedicalRecord mr) {
		medicalRecordDao.delete(mr);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
