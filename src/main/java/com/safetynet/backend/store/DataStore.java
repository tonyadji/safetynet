/**
 * 
 */
package com.safetynet.backend.store;

import java.util.List;

import com.safetynet.backend.entities.Firestation;
import com.safetynet.backend.entities.MedicalRecord;
import com.safetynet.backend.entities.Person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author tonys
 *
 */
@Getter @Setter @NoArgsConstructor
public class DataStore {
	
	private List<Person> persons;
	
	private List<Firestation> firestations;
	
	private List<MedicalRecord> medicalrecords;
	
}
