/**
 * 
 */
package com.safetynet.backend.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tonys
 *
 */
@Getter @Setter @NoArgsConstructor @ToString
public class PersonInfoDto {

	private String firstName;
	private String lastName;
	private String address;
	private int age;
	private String email;
	private List<String> medications;
	private List<String> allergies;
	
}
