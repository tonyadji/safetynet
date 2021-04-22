/**
 * 
 */
package com.safetynet.backend.mapper;

import java.time.LocalDate;

import com.safetynet.backend.dto.ChildInfoDto;
import com.safetynet.backend.dto.FloodPersonInfoDto;
import com.safetynet.backend.dto.LitePersonInfoDto;
import com.safetynet.backend.dto.PersonInfoDto;
import com.safetynet.backend.dto.PersonWithMedicalRecord;
import com.safetynet.backend.entities.MedicalRecord;
import com.safetynet.backend.entities.Person;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tonys
 *
 */
@Data @NoArgsConstructor
public class PersonMapper {

	public PersonWithMedicalRecord mergePersonWithRecord(Person p, MedicalRecord mr) {
		final PersonWithMedicalRecord personWithMedicalRecord = new PersonWithMedicalRecord();
		personWithMedicalRecord.setAddress(p.getAddress());
		personWithMedicalRecord.setAllergies(mr.getAllergies());
		personWithMedicalRecord.setBirthdate(mr.getBirthdate());
		personWithMedicalRecord.setEmail(p.getEmail());
		personWithMedicalRecord.setFirstName(p.getFirstName());
		personWithMedicalRecord.setLastName(p.getLastName());
		personWithMedicalRecord.setMedications(mr.getMedications());
		personWithMedicalRecord.setPhone(p.getPhone());
		return personWithMedicalRecord;
	}
	
	public PersonInfoDto toPersonInfoDto(PersonWithMedicalRecord p) {
		final PersonInfoDto dto = new PersonInfoDto();
		dto.setAddress(p.getAddress());
		dto.setFirstName(p.getFirstName());
		dto.setLastName(p.getLastName());
		dto.setEmail(p.getEmail());
		dto.setAge(getAgeFromBirthDate(p.getBirthdate()));
		dto.setAllergies(p.getAllergies());
		dto.setMedications(p.getMedications());
		return dto;
	}
	
	public LitePersonInfoDto toLitePersonInfoDto(PersonWithMedicalRecord p) {
		final LitePersonInfoDto dto = new LitePersonInfoDto();
		dto.setAddress(p.getAddress());
		dto.setFirstName(p.getFirstName());
		dto.setLastName(p.getLastName());
		dto.setPhone(p.getPhone());
		return dto;
	}
	
	public ChildInfoDto toChildInfoDto(PersonWithMedicalRecord p) {
		final ChildInfoDto dto = new ChildInfoDto();
		dto.setFirstName(p.getFirstName());
		dto.setLastName(p.getLastName());
		dto.setAge(getAgeFromBirthDate(p.getBirthdate()));
		return dto;
	}
	
	public FloodPersonInfoDto toFloodPersonInfoDto(PersonWithMedicalRecord p) {
		final FloodPersonInfoDto dto = new FloodPersonInfoDto();
		dto.setAge(getAgeFromBirthDate(p.getBirthdate()));
		dto.setAllergies(p.getAllergies());
		dto.setFirstName(p.getFirstName());
		dto.setLastName(p.getLastName());
		dto.setMedications(p.getMedications());
		return dto;
	}
	
	public int getAgeFromBirthDate(String dateOfBirth) {
		if(dateOfBirth == null) {
			throw new IllegalArgumentException("Cannot determine age based on [null] date");
		}
		String[] splitedDate = dateOfBirth.split("/");
		if(splitedDate.length < 3) {
			throw new IllegalArgumentException("Date format should be dd/MM/yyyy");
		}
		int yearOfBirth = Integer.parseInt(splitedDate[2]);
		return LocalDate.now().getYear() - yearOfBirth;
	}
}
