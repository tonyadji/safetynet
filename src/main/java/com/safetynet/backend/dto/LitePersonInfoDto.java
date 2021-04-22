/**
 * 
 */
package com.safetynet.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tonys
 *
 */
@Data @NoArgsConstructor
public class LitePersonInfoDto {

	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String phone;
}
