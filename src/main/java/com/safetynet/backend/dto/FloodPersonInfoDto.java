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
public class FloodPersonInfoDto {

	private String firstName;
	
	private String lastName;
	
	private int age;
	
	private List<String> medications;
	private List<String> allergies;
}
