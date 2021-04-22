/**
 * 
 */
package com.safetynet.backend.dao;

import org.springframework.stereotype.Repository;

import com.safetynet.backend.entities.Person;
import com.safetynet.backend.store.IManageDataStore;

/**
 * @author tonys
 *
 */
@Repository
public class PersonDAO extends AbstractDAO<Person>{

	public PersonDAO(IManageDataStore storeManager) {
		this.storeManager = storeManager;
	}
	
}
