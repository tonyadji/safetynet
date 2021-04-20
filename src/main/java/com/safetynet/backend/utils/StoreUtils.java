/**
 * 
 */
package com.safetynet.backend.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.safetynet.backend.entities.Firestation;
import com.safetynet.backend.entities.MedicalRecord;
import com.safetynet.backend.entities.Person;
import com.safetynet.backend.store.DataStore;

/**
 * @author tonys
 *
 */
public class StoreUtils<T> {

	public T addOrUpdateToStore(DataStore dataStore, T entity, Class<? extends Object> clazz) {
		if(clazz.equals(Person.class)) {
			savePerson(dataStore,(Person)entity);
		}
		if(clazz.equals(Firestation.class)) {
			saveFirestaion(dataStore,(Firestation)entity);
		}
		if(clazz.equals(MedicalRecord.class)) {
			saveMedicalRecord(dataStore, (MedicalRecord)entity);
		}
		return entity;
	}

	public boolean deleteToStore(DataStore dataStore, T entity, Class<? extends Object> clazz) {
		if(clazz.equals(Person.class)) {
			return dataStore.getPersons().remove((Person)entity);
		}
		if(clazz.equals(Firestation.class)) {
			return dataStore.getFirestations().remove((Firestation)entity);
		}
		if(clazz.equals(MedicalRecord.class)) {
			return dataStore.getMedicalrecords().remove((MedicalRecord)entity);
		}
		return false;
	}
	
	private Person savePerson(DataStore dataStore, Person entityToSave) {
		List<Person> filteredPersons = dataStore.getPersons()
				.stream()
				.filter(
						p->p.getFirstName().equals(entityToSave.getFirstName()) &&
						p.getLastName().equals(entityToSave.getLastName()))
				.collect(Collectors.toList());
		if(filteredPersons.isEmpty()) {
			dataStore.getPersons().add(entityToSave);
		}else {
			int index = dataStore.getPersons().indexOf(filteredPersons.get(0));
			dataStore.getPersons().remove(index);
			dataStore.getPersons().add(index,entityToSave);
		}
		return entityToSave;
	}
	
	private MedicalRecord saveMedicalRecord(DataStore dataStore, MedicalRecord entityToSave) {
		List<MedicalRecord> filteredRecords = dataStore.getMedicalrecords()
				.stream()
				.filter(
						p->p.getFirstName().equals(entityToSave.getFirstName()) &&
						p.getLastName().equals(entityToSave.getLastName()))
				.collect(Collectors.toList());
		if(filteredRecords.isEmpty()) {
			dataStore.getMedicalrecords().add(entityToSave);
		}else {
			int index = dataStore.getMedicalrecords().indexOf(filteredRecords.get(0));
			dataStore.getMedicalrecords().remove(index);
			dataStore.getMedicalrecords().add(index,entityToSave);
		}
		return entityToSave;
	}
	
	private Firestation saveFirestaion(DataStore dataStore, Firestation entityToSave) {
		List<Firestation> filteredFireStation = dataStore.getFirestations()
				.stream()
				.filter(
						p->p.getAddress().equals(entityToSave.getAddress()))
				.collect(Collectors.toList());
		if(filteredFireStation.isEmpty()) {
			dataStore.getFirestations().add(entityToSave);
		}else {
			int index = dataStore.getFirestations().indexOf(filteredFireStation.get(0));
			dataStore.getFirestations().remove(index);
			dataStore.getFirestations().add(index,entityToSave);
		}
		return entityToSave;
	}
	
}
