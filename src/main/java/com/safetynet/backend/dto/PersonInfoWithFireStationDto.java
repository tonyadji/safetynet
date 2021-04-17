/**
 * 
 */
package com.safetynet.backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tonys
 *
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class PersonInfoWithFireStationDto {

	private int fireStationNumber;
	
	private List<PersonInfoDto> persons;
}
