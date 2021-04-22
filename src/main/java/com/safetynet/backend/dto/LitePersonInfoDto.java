/**
 * 
 */
package com.safetynet.backend.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tonys
 *
 */
@Setter @NoArgsConstructor
public class LitePersonInfoDto {

	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String phone;
}
