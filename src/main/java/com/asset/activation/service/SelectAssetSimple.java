package com.asset.activation.service;

import java.util.ArrayList;
import java.util.List;

import com.asset.activation.DTO.AssetDTO;
import com.asset.activation.DTO.AssetMapper;
import com.asset.activation.data.AssetEntity;
import com.asset.activation.data.AssetExceptionEnum;
import com.asset.activation.exception.AssetException;

public class SelectAssetSimple {
	
	
	
	/**
	 * Method to get the list of available assets through a simple way with date and volume conditions
	 * @param activationDate
	 * @param volumeNeeded
	 * @return list of available assets regarding the simple conditions
	 * @throws AssetException 
	 */
	static public List<AssetDTO> selectAssetsSimple(int volumeNeeded, List<AssetEntity> availableAssets) throws AssetException{
	
		List<AssetDTO> selectedAssets = new ArrayList<AssetDTO>();
		int remainVolume = volumeNeeded;
		for(AssetEntity asset:availableAssets) {
			selectedAssets.add(AssetMapper.toDTO(asset));
			remainVolume=remainVolume-asset.getVolume();
			if(remainVolume<=0)return selectedAssets;
		}
		if(selectedAssets.isEmpty()) {
			throw new AssetException(AssetExceptionEnum.ERROR_NO_ASSET_AVAILABLE.getMessage());
		}else if(remainVolume>0)
			throw new AssetException(AssetExceptionEnum.ERROR_VOLUME_TOO_BIG.getMessage());
		return selectedAssets;
	}
}
