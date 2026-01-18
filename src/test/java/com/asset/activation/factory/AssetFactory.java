package com.asset.activation.factory;

import java.util.List;

import com.asset.activation.data.AssetEntity;
import com.asset.activation.data.AvailabilityEntity;

public class AssetFactory {
	
	/**
	 * Method to create assetEntity
	 * @param code
	 * @param name
	 * @param clientType
	 * @param activationCost
	 * @param start
	 * @param end
	 * @param volume
	 * @return
	 */
	public AssetEntity createAsset(String code, String name, String clientType, double activationCost, List<AvailabilityEntity> availabilities, int volume) {
		AssetEntity asset = new AssetEntity();
		asset.setCode(code);
		asset.setName(name);
		asset.setClientType(clientType);
		asset.setActivationCost(activationCost);
		asset.setVolume(volume);
		asset.setAvailability(availabilities);
		
		return asset;
	}
}

