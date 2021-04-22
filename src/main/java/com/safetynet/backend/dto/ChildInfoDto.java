/**
 * 
 */
package com.safetynet.backend.dto;

import java.util.List;

import com.safetynet.backend.entities.Person;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tonys
 *
 */
@Data @NoArgsConstructor
public class ChildInfoDto {

	private String firstName;
	private String lastName;
	private int age;
	private List<Person> familyMembers;
}
