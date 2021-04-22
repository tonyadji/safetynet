package com.safetynet.backend.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynet.backend.entities.Firestation;
import com.safetynet.backend.entities.MedicalRecord;
import com.safetynet.backend.entities.Person;
import com.safetynet.backend.store.DataStore;
import com.safetynet.backend.store.IManageDataStore;

@ExtendWith(MockitoExtension.class)
class AbstractDaoTest {

	@Mock
	private IManageDataStore mockManageStore;

	@InjectMocks
	private ConcreteDao dao = new ConcreteDao(mockManageStore);

	private static DataStore dataStore;

	@BeforeEach
	void initDataStoreForThisClass() {
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
	void testSave() {
		// arrange
		final Person p = new Person();
		p.setFirstName("Tony");
		when(mockManageStore.getDataStoreInstance()).thenReturn(dataStore);
		// act
		final Person result = dao.save(p);

		// assert
		assertThat(result.getFirstName()).isEqualTo(p.getFirstName());
	}

	@Test
	void testDelete() {
		// arrange
		final Person p = getPersonWith("Dany", "Simons", "dany@simons.com", "Los Angeles", "237698461375");
		when(mockManageStore.getDataStoreInstance()).thenReturn(dataStore);
		// act
		final boolean result = dao.delete(p);

		// assert
		assertTrue(result);
		assertThat(dataStore.getPersons()).hasSize(1);
	}

	class ConcreteDao extends AbstractDAO<Person> {

		public ConcreteDao(IManageDataStore store) {
			this.storeManager = store;
		}
	}
}
