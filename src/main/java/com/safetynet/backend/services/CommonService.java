/**
 * 
 */
package com.safetynet.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.safetynet.backend.dto.PersonInfoDto;
import com.safetynet.backend.dto.PersonWithMedicalRecord;
import com.safetynet.backend.entities.Firestation;
import com.safetynet.backend.entities.MedicalRecord;
import com.safetynet.backend.entities.Person;
import com.safetynet.backend.mapper.PersonMapper;
import com.safetynet.backend.store.IManageDataStore;

/**
 * @author tonys
 *
 */
@Service
public class CommonService {

	private IManageDataStore storeManager;

	private PersonMapper personMapper;

	public CommonService(IManageDataStore storeManager) {
		this.storeManager = storeManager;
		personMapper = new PersonMapper();
	}

	public List<String> getPhoneListWithStationNumber(int fireStationNumber) {
		final List<Firestation> fireStations = storeManager.getDataStoreInstance().getFirestations().stream()
				.filter(station -> station.getStation() == fireStationNumber).collect(Collectors.toList());
		if(fireStations.isEmpty()) {
			throw new RuntimeException("No firestation matches that number");
		}
		final String stationAdress = fireStations.get(0).getAddress();
		return storeManager.getDataStoreInstance().getPersons().stream()
				.filter(person -> person.getAddress().equals(stationAdress)).map(Person::getPhone)
				.collect(Collectors.toList());
	}

	public List<PersonInfoDto> getPersonInfoList(String firstName, String lastName) {
		final List<Person> persons = storeManager.getDataStoreInstance().getPersons().stream()
				.filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
				.collect(Collectors.toList());
		List<PersonInfoDto> personListToReturn = new ArrayList<>();
		final List<MedicalRecord> medicalRecords = storeManager.getDataStoreInstance().getMedicalrecords();
		for (Person p : persons) {
			MedicalRecord medicalRecord = medicalRecords.stream()
					.filter(record -> record.getFirstName().equals(firstName) && record.getLastName().equals(lastName))
					.collect(Collectors.toList()).get(0);
			PersonWithMedicalRecord pwmr = personMapper.mergePersonWithRecord(p, medicalRecord);
			personListToReturn.add(personMapper.toPersonInfoDto(pwmr));
		}
		return personListToReturn;
	}

	public List<String> getAllEmailsInCity(String city) {
		final List<Person> persons = storeManager.getDataStoreInstance().getPersons();
		return persons.stream().filter(p -> p.getCity().equalsIgnoreCase(city)).map(Person::getEmail)
				.collect(Collectors.toList());
	}

}
