/**
 * 
 */
package com.safetynet.backend.entities;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tonys
 *
 */
@Data @NoArgsConstructor
public class MedicalRecord {

	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;
}
