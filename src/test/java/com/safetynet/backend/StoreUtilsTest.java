/**
 * 
 */
package com.safetynet.backend;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetynet.backend.entities.Firestation;
import com.safetynet.backend.entities.MedicalRecord;
import com.safetynet.backend.entities.Person;
import com.safetynet.backend.store.DataStore;
import com.safetynet.backend.utils.StoreUtils;

/**
 * @author tonys
 *
 */
class StoreUtilsTest {

	private StoreUtils<Person> personStore = new StoreUtils<>();
	private StoreUtils<MedicalRecord> medicalStore = new StoreUtils<>();
	private StoreUtils<Firestation> stationStore = new StoreUtils<>();

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

	/**
	 * Test method for
	 * {@link com.safetynet.backend.utils.StoreUtils#addOrUpdateToStore(com.safetynet.backend.store.DataStore, java.lang.Object, java.lang.Class)}.
	 */
	@Test
	void testAddOrUpdatePersonToStore_shouldAddEntity() {
		// arrange
		final Person p = getPersonWith("Tony", "Adji", "person@test.com", "Miami", "780988123");

		// act
		personStore.addOrUpdateToStore(dataStore, p, p.getClass());

		// assert
		assertThat(dataStore.getPersons().get(1).getEmail()).isEqualTo("person@test.com");
	}

	@Test
	void testAddOrUpdatePersonToStore_shouldUpdateEntity() {
		// arrange
		final Person p = getPersonWith("person", "test", "person@test.com", "Miami", "780988123");

		// act
		personStore.addOrUpdateToStore(dataStore, p, p.getClass());

		// assert
		assertThat(dataStore.getPersons()).contains(p);
	}

	/**
	 * Test method for
	 * {@link com.safetynet.backend.utils.StoreUtils#deleteToStore(com.safetynet.backend.store.DataStore, java.lang.Object, java.lang.Class)}.
	 */
	@Test
	void testDeletePersonToStore() {
		// arrange
		final Person p = getPersonWith("Tony", "Adji", "tonysimoandji@gmail.com", "Los Angeles", "237698460075");

		// act
		personStore.deleteToStore(dataStore, p, p.getClass());

		// assert
		assertThat(dataStore.getPersons()).hasSize(1);
	}

	/**
	 * Test method for
	 * {@link com.safetynet.backend.utils.StoreUtils#addOrUpdateToStore(com.safetynet.backend.store.DataStore, java.lang.Object, java.lang.Class)}.
	 */
	@Test
	void testAddOrUpdateMedicalRecordToStore_shouldAddEntity() {
		// arrange
		final MedicalRecord mr = getMedicalRecordWith("Omer", "Simpson", "17/02/1990");

		// act
		medicalStore.addOrUpdateToStore(dataStore, mr, mr.getClass());

		// assert
		assertThat(dataStore.getMedicalrecords().get(1).getFirstName()).isEqualTo("Tony");
	}

	@Test
	void testAddOrUpdateMedicalRecordToStore_shouldUpdateEntity() {
		// arrange
		final MedicalRecord mr = getMedicalRecordWith("Dany", "Simons", "17/02/2000");

		// act
		medicalStore.addOrUpdateToStore(dataStore, mr, mr.getClass());

		// assert
		assertThat(dataStore.getMedicalrecords().get(0).getBirthdate()).isEqualTo("17/02/2000");
	}

	/**
	 * Test method for
	 * {@link com.safetynet.backend.utils.StoreUtils#deleteToStore(com.safetynet.backend.store.DataStore, java.lang.Object, java.lang.Class)}.
	 */
	@Test
	void testDeleteMedicalRecordToStore() {
		// arrange
		final MedicalRecord mr = getMedicalRecordWith("Dany", "Simons", "17/02/1990");

		// act
		medicalStore.deleteToStore(dataStore, mr, mr.getClass());

		// assert
		assertThat(dataStore.getMedicalrecords()).hasSize(1);
	}

	/**
	 * Test method for
	 * {@link com.safetynet.backend.utils.StoreUtils#addOrUpdateToStore(com.safetynet.backend.store.DataStore, java.lang.Object, java.lang.Class)}.
	 */
	@Test
	void testAddOrUpdateFirestationToStore_shouldAddEntity() {
		// arrange
		final Firestation mr = new Firestation("Some address", 5);

		// act
		stationStore.addOrUpdateToStore(dataStore, mr, mr.getClass());

		// assert
		assertThat(dataStore.getFirestations()).hasSize(2);
	}

	@Test
	void testAddOrUpdateFirestationToStore_shouldUpdateEntity() {
		// arrange
		final Firestation mr = new Firestation("1509 Culver St", 5);

		// act
		stationStore.addOrUpdateToStore(dataStore, mr, mr.getClass());

		// assert
		assertThat(dataStore.getFirestations().get(0).getStation()).isEqualTo(5);
	}

	/**
	 * Test method for
	 * {@link com.safetynet.backend.utils.StoreUtils#deleteToStore(com.safetynet.backend.store.DataStore, java.lang.Object, java.lang.Class)}.
	 */
	@Test
	void testDeleteFirestationToStore() {
		// arrange
		final Firestation fs = new Firestation("1509 Culver St", 2);

		// act
		stationStore.deleteToStore(dataStore, fs, fs.getClass());

		// assert
		assertThat(dataStore.getFirestations()).hasSize(0);
	}
	
	@Test
	void testDeleteNotSupportedEntiy() {
		final boolean result = stationStore.deleteToStore(dataStore, new Firestation("some address", 0), Integer.class);
		assertThat(result).isFalse();
	}
}
