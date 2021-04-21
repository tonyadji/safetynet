/**
 * 
 */
package com.safetynet.backend.integration.store;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.safetynet.backend.store.DataStore;
import com.safetynet.backend.store.IManageDataStore;
import com.safetynet.backend.store.ManageWithJsonFIle;

/**
 * @author tonys
 *
 */
class ManageWithJsonFIleIT {
	
	private IManageDataStore manageDateStore = new ManageWithJsonFIle();

	/**
	 * Test method for {@link com.safetynet.backend.store.ManageWithJsonFIle#initDataStore()}.
	 */
	@Test
	void Given_CorrectPath_WhentestInitDataStore_ThenReturnTrue() {
		final String PATH_TO_JSON_FILE = "src/main/resources/data.json";
		final boolean result = manageDateStore.initDataStore(PATH_TO_JSON_FILE);
		assertTrue(result);
	}
	
	@Test
	void Given_FalsePath_WhentestInitDataStore_ThenReturnFalse() {
		final String PATH_TO_JSON_FILE = "src/main/resources/falsedata.json";
		final boolean result = manageDateStore.initDataStore(PATH_TO_JSON_FILE);
		assertFalse(result);
	}

	/**
	 * Test method for {@link com.safetynet.backend.store.ManageWithJsonFIle#getDataStoreInstance()}.
	 */
	@Test
	void testGetDataStoreInstance() {
		final DataStore store = manageDateStore.getDataStoreInstance();
		
		assertNotNull(store);
	}

}
