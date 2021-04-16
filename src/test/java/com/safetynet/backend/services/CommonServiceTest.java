/**
 * 
 */
package com.safetynet.backend.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.backend.entities.Person;
import com.safetynet.backend.store.DataStore;
import com.safetynet.backend.store.IManageDataStore;

/**
 * @author tonys
 *
 */
@ExtendWith(MockitoExtension.class)
class CommonServiceTest {

	@InjectMocks
	private CommonService commonService;
	
	@Mock
	private IManageDataStore mockManageStore;
	
	private static DataStore dataStore;
	
	@BeforeAll
	static void initDataStoreForThisClass() {
		dataStore = new DataStore();
		final List<Person> personsList = new ArrayList<>();
		personsList.add(getPersonWith("Dany", "Simons", "dany@simons.com", "Los Angeles", "237698461375"));
		dataStore.setPersons(personsList);		
	}
	
	private static Person getPersonWith(String firstName, String lastName, String email, String city, String phone ) {
		final Person person = new Person();
		person.setAddress("");
		person.setCity(city);
		person.setEmail(email);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPhone(phone);
		person.setZip("");
		return person;
	}
	
	@Test
	void given_correctCityName_whenGettingAllCityEmails_ThenReturnTheListOfPersons() {
		//arrange
		when(mockManageStore.getDataStoreInstance()).thenReturn(dataStore);
		final String correctCityname = "Los Angeles";
		
		//act
		final List<String> emailList = commonService.getAllEmailsInCity(correctCityname);
		
		//assert
		assertThat(emailList).isNotEmpty();
	}
	
	@Test
	void given_wrongCityName_whenGettingAllCityEmails_ThenReturnEmptyList() {
		//arrange
		when(mockManageStore.getDataStoreInstance()).thenReturn(dataStore);
		final String wrongCityName = "Atlanta";
		
		//act
		final List<String> emailList = commonService.getAllEmailsInCity(wrongCityName);
		
		//assert
		assertThat(emailList).isEmpty();
	}
}
