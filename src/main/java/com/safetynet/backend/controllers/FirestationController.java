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

import com.safetynet.backend.dao.facade.FacadeFireStationDAO;
import com.safetynet.backend.entities.Firestation;

/**
 * @author tonys
 *
 */
@RestController
@RequestMapping("/firestation")
public class FirestationController {

	private final FacadeFireStationDAO firestationDao;
	
	public FirestationController(FacadeFireStationDAO dao) {
		this.firestationDao = dao;
	}
	
	@PostMapping
	public ResponseEntity<Firestation> addFirestation(@RequestBody Firestation fs) {
		firestationDao.save(fs);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Firestation> updateFirestation(@RequestBody Firestation fs) {
		firestationDao.save(fs);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<Firestation> deleteFirestation(@RequestBody Firestation fs) {
		firestationDao.delete(fs);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
