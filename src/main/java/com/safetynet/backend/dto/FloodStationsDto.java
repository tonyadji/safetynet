/**
 * 
 */
package com.safetynet.backend.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * @author tonys
 *
 */
@Getter 
public class FloodStationsDto {
	
	public FloodStationsDto(int stationNumber) {
		this.stationNumber = stationNumber;
		this.personsPerAddress = new ArrayList<>();
	}

	private int stationNumber;
	
	private List<FloodPersonsAdress> personsPerAddress;
}
