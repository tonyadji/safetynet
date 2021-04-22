/**
 * 
 */
package com.safetynet.backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
