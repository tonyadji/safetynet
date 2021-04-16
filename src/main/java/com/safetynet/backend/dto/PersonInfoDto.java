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
public class PersonInfoDto {

	private String fullName;
	private String address;
	private int age;
	private List<String> medications;
	private List<String> allergies;
}
