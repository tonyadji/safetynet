/**
 * 
 */
package com.safetynet.backend.entities;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tonys
 *
 */
@Getter @Setter @EqualsAndHashCode @NoArgsConstructor
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
