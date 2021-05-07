/**
 * 
 */
package com.safetynet.backend.dao;

import org.springframework.stereotype.Repository;

import com.safetynet.backend.dao.facade.AbstractDAO;
import com.safetynet.backend.dao.facade.FacadePersonDAO;
import com.safetynet.backend.entities.Person;
import com.safetynet.backend.store.IManageDataStore;

/**
 * @author tonys
 *
 */
@Repository
public class PersonDAO extends AbstractDAO<Person> implements FacadePersonDAO{

	public PersonDAO(IManageDataStore storeManager) {
		this.storeManager = storeManager;
	}
	
}
