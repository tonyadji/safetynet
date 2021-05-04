/**
 * 
 */
package com.safetynet.backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author tonys
 *
 */
@Getter @NoArgsConstructor @AllArgsConstructor
public class PersonsInfoWithChildAndAdultCount {

	private List<LitePersonInfoDto> persons;
	
	private int children;
	
	private int adults;
}
