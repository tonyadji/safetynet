/**
 * 
 */
package com.safetynet.backend.dao.facade;

import com.safetynet.backend.entities.Firestation;

/**
 * @author tonys
 *
 */
public interface FacadeFireStationDAO {

	public Firestation save(Firestation entity);
	
	public boolean delete(Firestation entity);
}
