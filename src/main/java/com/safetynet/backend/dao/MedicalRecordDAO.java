/**
 * 
 */
package com.safetynet.backend.dao;

import org.springframework.stereotype.Repository;

import com.safetynet.backend.entities.MedicalRecord;
import com.safetynet.backend.store.IManageDataStore;

/**
 * @author tonys
 *
 */
@Repository
public class MedicalRecordDAO extends AbstractDAO<MedicalRecord>{

	public MedicalRecordDAO(IManageDataStore storeManager) {
		this.storeManager = storeManager;
	}
	
}
