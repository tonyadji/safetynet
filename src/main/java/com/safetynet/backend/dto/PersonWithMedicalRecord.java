/**
 * 
 */
package com.safetynet.backend.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tonys
 *
 */
@Data @NoArgsConstructor
public class PersonWithMedicalRecord {

	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;
	
}
