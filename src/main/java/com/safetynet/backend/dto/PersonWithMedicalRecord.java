/**
 * 
 */
package com.safetynet.backend.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tonys
 *
 */
@Getter @Setter @NoArgsConstructor
public class PersonWithMedicalRecord {

	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private String email;
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;
	
}
