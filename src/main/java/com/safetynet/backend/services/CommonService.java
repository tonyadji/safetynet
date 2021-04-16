/**
 * 
 */
package com.safetynet.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.safetynet.backend.dto.PersonInfoDto;
import com.safetynet.backend.entities.Person;
import com.safetynet.backend.store.IManageDataStore;

/**
 * @author tonys
 *
 */
@Service
public class CommonService {
	
	private IManageDataStore storeManager;
	
	public CommonService(IManageDataStore storeManager) {
		this.storeManager = storeManager;
	}

	public List<String> getAllEmailsInCity(String city){
		final List<Person> persons = storeManager.getDataStoreInstance().getPersons();
		return persons.stream()
				.filter(p -> p.getCity().equalsIgnoreCase(city))
				.map(Person::getEmail).collect(Collectors.toList());
	}
	
	public List<PersonInfoDto> getPersonInfoList(String firstName, String lastName){
		return null;
	}
}
