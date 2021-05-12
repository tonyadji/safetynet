/**
 * 
 */
package com.safetynet.backend.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.backend.dto.ChildInfoDto;
import com.safetynet.backend.dto.FloodStationsDto;
import com.safetynet.backend.dto.PersonInfoDto;
import com.safetynet.backend.dto.PersonInfoWithFireStationDto;
import com.safetynet.backend.dto.PersonsInfoWithChildAndAdultCount;
import com.safetynet.backend.entities.Firestation;
import com.safetynet.backend.entities.MedicalRecord;
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
		final List<MedicalRecord> medicalRecords = new ArrayList<>();
		final List<Firestation> fireStations = new ArrayList<>();
		personsList.add(getPersonWith("Dany", "Simons", "dany@simons.com", "Los Angeles", "237698461375"));
		personsList.add(getPersonWith("Tony", "Adji", "tonysimoandji@gmail.com", "Los Angeles", "237698460075"));
		medicalRecords.add(getMedicalRecordWith("Dany", "Simons", "17/02/1990"));
		medicalRecords.add(getMedicalRecordWith("Tony", "Adji", "17/02/2010"));
		fireStations.add(new Firestation("1509 Culver St", 2));

		dataStore.setPersons(personsList);
		dataStore.setMedicalrecords(medicalRecords);
		dataStore.setFirestations(fireStations);
	}

	private static Person getPersonWith(String firstName, String lastName, String email, String city, String phone) {
		final Person person = new Person();
		person.setAddress("1509 Culver St");
		person.setCity(city);
		person.setEmail(email);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPhone(phone);
		person.setZip("");
		return person;
	}

	private static MedicalRecord getMedicalRecordWith(String firstName, String lastName, String birthDate) {
		final MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstName(firstName);
		medicalRecord.setLastName(lastName);
		medicalRecord.setBirthdate(birthDate);
		medicalRecord.setAllergies(new ArrayList<>());
		medicalRecord.setMedications(new ArrayList<>());
		return medicalRecord;
	}

	@Test
	void given_correctCityName_whenGettingAllCityEmails_ThenReturnTheListOfPersons() {
		// arrange
		when(mockManageStore.getDataStoreInstance()).thenReturn(dataStore);
		final String correctCityname = "Los Angeles";

		// act
		final List<String> emailList = commonService.getAllEmailsInCity(correctCityname);

		// assert
		assertThat(emailList).isNotEmpty();
	}

	@Test
	void given_wrongCityName_whenGettingAllCityEmails_ThenReturnEmptyList() {
		// arrange
		when(mockManageStore.getDataStoreInstance()).thenReturn(dataStore);
		final String wrongCityName = "Atlanta";

		// act
		final List<String> emailList = commonService.getAllEmailsInCity(wrongCityName);

		// assert
		assertThat(emailList).isEmpty();
	}

	@Test
	void given_NamesThatDontMatchAnyPerson_whenGettingPersonInfo_ThenReturnEmptyList() {
		// arrange
		when(mockManageStore.getDataStoreInstance()).thenReturn(dataStore);
		final String firstName = "firstName";
		final String lastName = "lastName";

		// act
		final List<PersonInfoDto> resultList = commonService.getPersonInfoList(firstName, lastName);

		// assert
		assertThat(resultList).isEmpty();
	}

	@Test
	void given_NamesThatMatchPerson_whenGettingPersonInfo_ThenReturnTheList() {
		// arrange
		when(mockManageStore.getDataStoreInstance()).thenReturn(dataStore);
		final String firstName = "Dany";
		final String lastName = "Simons";

		// act
		final List<PersonInfoDto> resultList = commonService.getPersonInfoList(firstName, lastName);

		// assert
		assertThat(resultList).isNotEmpty();
	}

	@Test
	void given_WrongStationNumber_WhenGettingPhone_ThenThrowException() {
		// arrange
		when(mockManageStore.getDataStoreInstance()).thenReturn(dataStore);

		// act
		final List<String> result = commonService.getPhoneListWithStationNumber(3);
		// assert
		assertThat(result).isEmpty();
	}

	@Test
	void given_StationNumber_WhenGettingPhone_ThenReturnList() {
		// arrange
		when(mockManageStore.getDataStoreInstance()).thenReturn(dataStore);

		// act
		final List<String> phoneList = commonService.getPhoneListWithStationNumber(2);
		// assert
		assertThat(phoneList).isNotEmpty();
	}

	@Test
	void given_SomeChildren_WhenGetChildrenByAdress_ThenReturnList() {
		// arrange
		when(mockManageStore.getDataStoreInstance()).thenReturn(dataStore);

		// act
		final List<ChildInfoDto> result = commonService.getChildrenByAdress("1509 Culver St");

		// assert
		assertThat(result).isNotEmpty();
	}

	@Test
	void given_CorrectStationNumber_WhenGettingPersonsInfoAndCounting_ThenReturnTheObject() {
		// arrange
		when(mockManageStore.getDataStoreInstance()).thenReturn(dataStore);

		// act
		final PersonsInfoWithChildAndAdultCount personInfo = commonService.getPersonsWithChilAndAdultCount(2);

		// assert
		assertThat(personInfo.getChildren()).isEqualTo(1);

		// assert
	}

	@Test
	void given_WrongStationNumber_WhenGettingPersonsInfoAndCounting_ThenReturnEmptyList() {
		// arrange
		when(mockManageStore.getDataStoreInstance()).thenReturn(dataStore);

		// act
		final PersonsInfoWithChildAndAdultCount result = commonService.getPersonsWithChilAndAdultCount(9);

		// assert
		assertThat(result.getPersons()).isEmpty();
		assertThat(result.getAdults()).isEqualTo(-1);
		// assert
	}

	@Test
	void given_WrongStationAddress_WhenGettingPersonsInfoByAddress_ThenReturnEmptyListAnd0asStationnumber() {
		// arrange
		when(mockManageStore.getDataStoreInstance()).thenReturn(dataStore);

		// act
		final PersonInfoWithFireStationDto result = commonService.getPersonInfoByAdress("wrong address");

		// assert
		assertThat(result.getPersons()).isEmpty();
		assertThat(result.getFireStationNumber()).isEqualTo(-1);

	}

	@Test
	void given_CorrectStationNumber_WhenGettingPersonsInfoByAddress_ThenThrowException() {
		// arrange
		when(mockManageStore.getDataStoreInstance()).thenReturn(dataStore);

		// act
		final PersonInfoWithFireStationDto personInfo = commonService.getPersonInfoByAdress("1509 Culver St");

		// assert
		assertThat(personInfo.getFireStationNumber()).isEqualTo(2);

	}

	@Test
	void testGetFloodStations() {
		// arrange
		final List<String> stations = new ArrayList<>();
		stations.add("2");
		when(mockManageStore.getDataStoreInstance()).thenReturn(dataStore);

		// act
		final List<FloodStationsDto> floodList = commonService.getFloodStations(stations);
		
		//assert
		assertThat(floodList).isNotEmpty();
	}
}
