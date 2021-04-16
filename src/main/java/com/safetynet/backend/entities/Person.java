/**
 * 
 */
package com.safetynet.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tonys
 *
 */
@Data @NoArgsConstructor
public class Person {

	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;
}
