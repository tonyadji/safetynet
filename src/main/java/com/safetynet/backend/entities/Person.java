/**
 * 
 */
package com.safetynet.backend.entities;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tonys
 *
 */
@Data @NoArgsConstructor
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
