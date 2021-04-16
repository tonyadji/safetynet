/**
 * 
 */
package com.safetynet.backend.store;

import java.util.List;

import com.safetynet.backend.entities.Firestation;
import com.safetynet.backend.entities.MedicalRecord;
import com.safetynet.backend.entities.Person;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author tonys
 *
 */
@Data @NoArgsConstructor
public class DataStore {
	
	private List<Person> persons;
	
	private List<Firestation> firestations;
	
	private List<MedicalRecord> medicalrecords;
	
}
