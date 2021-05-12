/**
 * 
 */
package com.safetynet.backend.entities;

import javax.validation.constraints.NotEmpty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tonys
 *
 */
@Getter @Setter @EqualsAndHashCode @NoArgsConstructor @ToString
public class Person {

	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	private String address;
	@NotEmpty
	private String city;
	@NotEmpty
	private String zip;
	@NotEmpty
	private String phone;
	@NotEmpty
	private String email;
}
