/**
 * 
 */
package com.safetynet.backend.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tonys
 *
 */
@Data @NoArgsConstructor
public class FloodPersonsAdress {
	
	public FloodPersonsAdress(String address) {
		this.address = address;
		persons = new ArrayList<>();
	}

	private String address;
	
	private List<FloodPersonInfoDto> persons;
}
