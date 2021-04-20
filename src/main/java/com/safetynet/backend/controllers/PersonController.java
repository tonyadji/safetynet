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

import com.safetynet.backend.dao.PersonDAO;
import com.safetynet.backend.entities.Person;

/**
 * @author tonys
 *
 */
@RestController
@RequestMapping("/person")
public class PersonController {

	private final PersonDAO personDao;
	
	public PersonController(PersonDAO dao) {
		this.personDao = dao;
	}
	
	@PostMapping
	public ResponseEntity<Person> addPerson(@RequestBody Person p) {
		personDao.save(p);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Person> updatePerson(@RequestBody Person p) {
		personDao.save(p);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<Person> deletePerson(@RequestBody Person p) {
		personDao.delete(p);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
