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
public class PersonsInfoWithChildAndAdultCount {

	private List<LitePersonInfoDto> persons = new ArrayList<>();
	
	private int children;
	
	private int adults;
}
