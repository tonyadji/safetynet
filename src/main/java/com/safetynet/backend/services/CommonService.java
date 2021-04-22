/**
 * 
 */
package com.safetynet.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.safetynet.backend.dto.ChildInfoDto;
import com.safetynet.backend.dto.FloodPersonInfoDto;
import com.safetynet.backend.dto.FloodPersonsAdress;
import com.safetynet.backend.dto.FloodStationsDto;
import com.safetynet.backend.dto.LitePersonInfoDto;
import com.safetynet.backend.dto.PersonInfoDto;
import com.safetynet.backend.dto.PersonInfoWithFireStationDto;
import com.safetynet.backend.dto.PersonWithMedicalRecord;
import com.safetynet.backend.dto.PersonsInfoWithChildAndAdultCount;
import com.safetynet.backend.entities.Firestation;
import com.safetynet.backend.entities.MedicalRecord;
import com.safetynet.backend.entities.Person;
import com.safetynet.backend.mapper.PersonMapper;
import com.safetynet.backend.store.DataStore;
import com.safetynet.backend.store.IManageDataStore;

/**
 * @author tonys
 *
 */
@Service
public class CommonService {

	private IManageDataStore storeManager;

	private PersonMapper personMapper;
	
	private static final int ADULT_AGE = 18;

	public CommonService(IManageDataStore storeManager) {
		this.storeManager = storeManager;
		personMapper = new PersonMapper();
	}

	public PersonsInfoWithChildAndAdultCount getPersonsWithChilAndAdultCount(int stationNumber) {
		final List<Firestation> fireStations = storeManager.getDataStoreInstance().getFirestations().stream()
				.filter(station -> station.getStation() == stationNumber).collect(Collectors.toList());
		if (fireStations.isEmpty()) {
			throw new RuntimeException("No firestation matches that number");
		}
		int childrenNumber = 0;
		int adultNumber = 0;
		final List<LitePersonInfoDto> personsListToReturn = new ArrayList<>();
		final List<Person> personCoveredByStation = new ArrayList<>();
		fireStations.forEach(station -> 
			personCoveredByStation.addAll(storeManager.getDataStoreInstance().getPersons().stream()
					.filter(person -> person.getAddress().equals(station.getAddress())).collect(Collectors.toList()))
		);
		final List<MedicalRecord> medicalRecords = storeManager.getDataStoreInstance().getMedicalrecords();
		for (Person p : personCoveredByStation) {
			MedicalRecord medicalRecord = medicalRecords.stream()
					.filter(record -> record.getFirstName().equals(p.getFirstName())
							&& record.getLastName().equals(p.getLastName()))
					.collect(Collectors.toList()).get(0);
			PersonWithMedicalRecord pwmr = personMapper.mergePersonWithRecord(p, medicalRecord);
			if(personMapper.getAgeFromBirthDate(pwmr.getBirthdate()) > ADULT_AGE) {
				adultNumber++;
			}else {
				childrenNumber++;
			}
			personsListToReturn.add(personMapper.toLitePersonInfoDto(pwmr));
		}
		return new PersonsInfoWithChildAndAdultCount(personsListToReturn, childrenNumber, adultNumber);
	}

	public List<ChildInfoDto> getChildrenByAdress(String adress) {
		final List<Person> persons = storeManager.getDataStoreInstance().getPersons().stream()
				.filter(p -> p.getAddress().equals(adress)).collect(Collectors.toList());
		final List<ChildInfoDto> listOfChildren = new ArrayList<>();
		final List<MedicalRecord> medicalRecords = storeManager.getDataStoreInstance().getMedicalrecords();
		for (Person p : persons) {
			final List<MedicalRecord> records = medicalRecords.stream()
					.filter(record -> record.getFirstName().equals(p.getFirstName())
							&& record.getLastName().equals(p.getLastName()))
					.collect(Collectors.toList());
			if(!records.isEmpty()) {
				MedicalRecord medicalRecord = records.get(0);
				PersonWithMedicalRecord pwmr = personMapper.mergePersonWithRecord(p, medicalRecord);
				ChildInfoDto childInfo = personMapper.toChildInfoDto(pwmr);
				if (childInfo.getAge() <= ADULT_AGE) {
					childInfo.setFamilyMembers(persons.stream()
							.filter(familyMember -> familyMember.getLastName().equals(childInfo.getLastName())
									&& !familyMember.getFirstName().equals(childInfo.getFirstName()))
							.collect(Collectors.toList()));
					listOfChildren.add(childInfo);
				}
			}
			
		}
		return listOfChildren;
	}

	public List<String> getPhoneListWithStationNumber(int fireStationNumber) {
		final List<Firestation> fireStations = storeManager.getDataStoreInstance().getFirestations().stream()
				.filter(station -> station.getStation() == fireStationNumber).collect(Collectors.toList());
		if (fireStations.isEmpty()) {
			throw new RuntimeException("No firestation matches that number");
		}
		final List<String> phoneList = new ArrayList<>();
		for (Firestation station : fireStations) {
			phoneList.addAll(storeManager.getDataStoreInstance().getPersons().stream()
					.filter(person -> person.getAddress().equals(station.getAddress())).map(Person::getPhone)
					.collect(Collectors.toList()));
		}
		return phoneList;
	}

	public PersonInfoWithFireStationDto getPersonInfoByAdress(String adress) {
		final List<Firestation> fireStations = storeManager.getDataStoreInstance().getFirestations().stream()
				.filter(station -> station.getAddress().equals(adress)).collect(Collectors.toList());
		if (fireStations.isEmpty()) {
			throw new RuntimeException("No firestation matches that adress");
		}
		int station = fireStations.get(0).getStation();
		final List<Person> persons = storeManager.getDataStoreInstance().getPersons().stream()
				.filter(p -> p.getAddress().equals(adress)).collect(Collectors.toList());
		final List<MedicalRecord> medicalRecords = storeManager.getDataStoreInstance().getMedicalrecords();
		List<PersonInfoDto> personListToReturn = new ArrayList<>();
		for (Person p : persons) {
			MedicalRecord medicalRecord = medicalRecords.stream()
					.filter(record -> record.getFirstName().equals(p.getFirstName())
							&& record.getLastName().equals(p.getLastName()))
					.collect(Collectors.toList()).get(0);
			PersonWithMedicalRecord pwmr = personMapper.mergePersonWithRecord(p, medicalRecord);
			personListToReturn.add(personMapper.toPersonInfoDto(pwmr));
		}
		return new PersonInfoWithFireStationDto(station, personListToReturn);
	}
	
	public List<FloodStationsDto> getFloodStations(List<String> stationNumbers) {

		final List<MedicalRecord> medicalRecords = storeManager.getDataStoreInstance().getMedicalrecords();
		final List<FloodStationsDto> listToReturn = new ArrayList<>();
		for(String stationNumberAsString: stationNumbers) {
			int stationNumber = Integer.parseInt(stationNumberAsString);
			//get stations related to the current number
			final List<Firestation> fireStations = storeManager.getDataStoreInstance().getFirestations().stream()
					.filter(station -> station.getStation() == stationNumber).collect(Collectors.toList());
			//get the address and find the persons at that address
			final FloodStationsDto floodStationsDto = new FloodStationsDto(stationNumber);
			for(Firestation fireStation: fireStations) {
				final FloodPersonsAdress floodPersonsAdress = new FloodPersonsAdress(fireStation.getAddress());
				final List<Person> persons = storeManager.getDataStoreInstance().getPersons().stream()
						.filter(p -> p.getAddress().equals(fireStation.getAddress())).collect(Collectors.toList());
				for (Person p : persons) {
					MedicalRecord medicalRecord = medicalRecords.stream()
							.filter(record -> record.getFirstName().equals(p.getFirstName())
									&& record.getLastName().equals(p.getLastName()))
							.collect(Collectors.toList()).get(0);
					PersonWithMedicalRecord pwmr = personMapper.mergePersonWithRecord(p, medicalRecord);
					final FloodPersonInfoDto floodPersonInfoDto = personMapper.toFloodPersonInfoDto(pwmr);
					floodPersonsAdress.getPersons().add(floodPersonInfoDto);
				}
				floodStationsDto.getPersonsPerAddress().add(floodPersonsAdress);
			}
			listToReturn.add(floodStationsDto);
		}
		return listToReturn;
	}

	public List<PersonInfoDto> getPersonInfoList(String firstName, String lastName) {
		final List<Person> persons = storeManager.getDataStoreInstance().getPersons().stream()
				.filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
				.collect(Collectors.toList());
		List<PersonInfoDto> personListToReturn = new ArrayList<>();
		final List<MedicalRecord> medicalRecords = storeManager.getDataStoreInstance().getMedicalrecords();
		for (Person p : persons) {
			MedicalRecord medicalRecord = medicalRecords.stream()
					.filter(record -> record.getFirstName().equals(p.getFirstName())
							&& record.getLastName().equals(p.getLastName()))
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

	public DataStore getDataStore() {
		return this.storeManager.getDataStoreInstance();
	}
}
