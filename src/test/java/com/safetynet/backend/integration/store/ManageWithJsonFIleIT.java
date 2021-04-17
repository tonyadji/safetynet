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
	void testInitDataStore() {
		final boolean result = manageDateStore.initDataStore();
		assertTrue(result);
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
