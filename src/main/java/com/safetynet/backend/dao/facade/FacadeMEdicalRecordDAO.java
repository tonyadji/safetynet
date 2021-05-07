/**
 * 
 */
package com.safetynet.backend.dao.facade;

import com.safetynet.backend.entities.MedicalRecord;

/**
 * @author tonys
 *
 */
public interface FacadeMEdicalRecordDAO {

	public MedicalRecord save(MedicalRecord entity);
	
	public boolean delete(MedicalRecord entity);
}
