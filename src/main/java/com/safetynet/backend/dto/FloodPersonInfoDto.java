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
@Setter @Getter @NoArgsConstructor @ToString
public class FloodPersonInfoDto {

	private String firstName;
	
	private String lastName;
	
	private int age;
	
	private List<String> medications;
	private List<String> allergies;
}
