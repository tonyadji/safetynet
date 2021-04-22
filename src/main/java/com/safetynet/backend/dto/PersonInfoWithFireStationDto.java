/**
 * 
 */
package com.safetynet.backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tonys
 *
 */
@Getter @NoArgsConstructor @AllArgsConstructor
public class PersonInfoWithFireStationDto {

	private int fireStationNumber;
	
	private List<PersonInfoDto> persons;
}
