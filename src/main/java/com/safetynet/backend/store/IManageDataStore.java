/**
 * 
 */
package com.safetynet.backend.store;

/**
 * @author tonys
 *
 */
public interface IManageDataStore {

	public boolean initDataStore();
	
	public DataStore getDataStoreInstance();
}
