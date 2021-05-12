/**
 * 
 */
package com.safetynet.backend.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

/**
 * @author tonys
 *
 */
@Getter @ToString
public class FloodStationsDto {
	
	public FloodStationsDto(int stationNumber) {
		this.stationNumber = stationNumber;
		this.personsPerAddress = new ArrayList<>();
	}

	private int stationNumber;
	
	private List<FloodPersonsAdress> personsPerAddress;
}
