package com.safetynet.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.backend.store.DataStore;
import com.safetynet.backend.store.IManageDataStore;
import com.safetynet.backend.store.ManageWithJsonFIle;

@SpringBootApplication
public class SafetyNetBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetBackendApplication.class, args);
		
		IManageDataStore storeManager = new ManageWithJsonFIle();
		try {
			DataStore ds = storeManager.getDataStoreInstance();
			System.out.println("store initialized with "+ds.getPersons().size()+" persons");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
