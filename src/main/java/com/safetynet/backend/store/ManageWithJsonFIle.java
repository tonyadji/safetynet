/**
 * 
 */
package com.safetynet.backend.store;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author tonys
 *
 */
public class ManageWithJsonFIle implements IManageDataStore {
	
	private static final String PATH_TO_JSON_FILE = "src/main/resources/data.json";
	
	private DataStore store;
	
	private static final Logger log = LoggerFactory.getLogger(ManageWithJsonFIle.class);

	@Override
	public void initDataStore() {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			this.store = mapper.readValue(new File(PATH_TO_JSON_FILE), DataStore.class);
		} catch (final IOException e) {
			log.error("Echec parsing du json :{}  Erreur: {}" ,PATH_TO_JSON_FILE, e.getMessage());
		}
	}

	@Override
	public DataStore getDataStoreInstance() {
		if(store == null) {
			initDataStore();
		}
		return store;
	}

}
