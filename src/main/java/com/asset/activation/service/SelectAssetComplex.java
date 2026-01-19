package com.asset.activation.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.asset.activation.DTO.AssetDTO;
import com.asset.activation.DTO.AssetMapper;
import com.asset.activation.data.AssetEntity;
import com.asset.activation.data.AssetExceptionEnum;
import com.asset.activation.exception.AssetException;

public class SelectAssetComplex {
	/**
	 * Method to get the list of available assets with more parameters and complexity
	 * @param activationDate
	 * @param volumeNeeded
	 * @return list of available assets regarding the cost and the volume of each assets to select the better choices
	 * @throws AssetException 
	 */
	static public List<AssetDTO> selectAssetsComplex(int volumeNeeded, List<AssetEntity> availableAssets) throws AssetException {
		List<AssetDTO> selectedAssets = new ArrayList<AssetDTO>();
		int remainVolume = volumeNeeded;
		
		//Sorting the list by efficiency of each asset. cost/volume = price for 1Kw
		availableAssets.sort(Comparator.comparingDouble(assetEntity -> (double) assetEntity.getActivationCost() / assetEntity.getVolume()));
		
		for(AssetEntity asset:availableAssets) {
			selectedAssets.add(AssetMapper.toDTO(asset));
			remainVolume-=asset.getVolume();
			if(remainVolume<=0)return selectedAssets;
		}
		if(selectedAssets.isEmpty()) {
			throw new AssetException(AssetExceptionEnum.ERROR_NO_ASSET_AVAILABLE.getMessage());
		}else if(remainVolume>0)
			throw new AssetException(AssetExceptionEnum.ERROR_VOLUME_TOO_BIG.getMessage());
		return selectedAssets;
	}
}
