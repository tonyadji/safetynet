/**
 * 
 */
package com.safetynet.backend.store;

/**
 * @author tonys
 *
 */
public interface IManageDataStore {

	public void initDataStore();
	
	public DataStore getDataStoreInstance();
}
