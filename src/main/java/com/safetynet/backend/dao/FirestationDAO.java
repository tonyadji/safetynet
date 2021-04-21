/**
 * 
 */
package com.safetynet.backend.dao;

import org.springframework.stereotype.Repository;

import com.safetynet.backend.entities.Firestation;
import com.safetynet.backend.store.IManageDataStore;

/**
 * @author tonys
 *
 */
@Repository
public class FirestationDAO extends AbstractDAO<Firestation>{

	public FirestationDAO(IManageDataStore storeManager) {
		this.storeManager = storeManager;
	}
	
}
