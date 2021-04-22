/**
 * 
 */
package com.safetynet.backend.dto;

import java.util.List;

import com.safetynet.backend.entities.Person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tonys
 *
 */
@Getter @Setter @NoArgsConstructor
public class ChildInfoDto {

	private String firstName;
	private String lastName;
	private int age;
	private List<Person> familyMembers;
}
