/**
 * 
 */
package com.safetynet.backend.dao;

import org.springframework.stereotype.Repository;

import com.safetynet.backend.dao.facade.AbstractDAO;
import com.safetynet.backend.dao.facade.FacadeFireStationDAO;
import com.safetynet.backend.entities.Firestation;
import com.safetynet.backend.store.IManageDataStore;

/**
 * @author tonys
 *
 */
@Repository
public class FirestationDAO extends AbstractDAO<Firestation> implements FacadeFireStationDAO{

	public FirestationDAO(IManageDataStore storeManager) {
		this.storeManager = storeManager;
	}
	
}
