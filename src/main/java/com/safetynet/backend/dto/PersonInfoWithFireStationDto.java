/**
 * 
 */
package com.safetynet.backend.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author tonys
 *
 */
@Getter @NoArgsConstructor @AllArgsConstructor @ToString
public class PersonInfoWithFireStationDto {

	private int fireStationNumber;
	
	private List<PersonInfoDto> persons = new ArrayList<>();
}
