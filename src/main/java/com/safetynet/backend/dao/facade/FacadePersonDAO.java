/**
 * 
 */
package com.safetynet.backend.dao.facade;

import com.safetynet.backend.entities.Person;

/**
 * @author tonys
 *
 */
public interface FacadePersonDAO {

	public Person save(Person entity);
	
	public boolean delete(Person entity);
}
