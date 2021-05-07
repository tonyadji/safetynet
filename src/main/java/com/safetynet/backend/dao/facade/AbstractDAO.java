/**
 * 
 */
package com.safetynet.backend.dao.facade;

import com.safetynet.backend.store.IManageDataStore;
import com.safetynet.backend.utils.StoreUtils;

/**
 * @author tonys
 *
 */
public abstract class AbstractDAO<T> {
	
	private StoreUtils<T> storeUtils = new StoreUtils<>();
	
	protected IManageDataStore storeManager;

	public T save(T entityToSave) {
		return storeUtils.addOrUpdateToStore(storeManager.getDataStoreInstance(), entityToSave, entityToSave.getClass());
	}
	
	public boolean delete(T entityToDelete) {
		return storeUtils.deleteToStore(storeManager.getDataStoreInstance(), entityToDelete, entityToDelete.getClass());
	}
	
}
