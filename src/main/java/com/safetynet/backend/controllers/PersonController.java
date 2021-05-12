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

import com.safetynet.backend.dao.facade.FacadePersonDAO;
import com.safetynet.backend.entities.Person;

/**
 * @author tonys
 *
 */
@RestController
@RequestMapping("/person")
public class PersonController {

	private final Logger LOG = LogManager.getLogger(getClass());
	
	private final FacadePersonDAO personDao;
	
	public PersonController(FacadePersonDAO dao) {
		this.personDao = dao;
	}
	
	@PostMapping
	public ResponseEntity<Person> addPerson(@RequestBody Person p) {
		LOG.debug("[ POST /person ]");
		personDao.save(p);
		LOG.info("Person saved {}",p);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Person> updatePerson(@RequestBody Person p) {
		LOG.debug("[ PUT /person ]");
		personDao.save(p);
		LOG.info("Person updated {}",p);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<Person> deletePerson(@RequestBody Person p) {
		LOG.debug("[ DELETE /person ]");
		personDao.delete(p);
		LOG.info("Person deleted {}",p);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
