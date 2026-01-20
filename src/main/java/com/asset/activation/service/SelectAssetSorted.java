package com.asset.activation.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.asset.activation.DTO.AssetDTO;
import com.asset.activation.DTO.AssetMapper;
import com.asset.activation.data.AssetEntity;
import com.asset.activation.data.AssetExceptionEnum;
import com.asset.activation.exception.AssetException;

public class SelectAssetSorted implements SelectAssetAlgo {
	/**
	 * Method to get the list of available assets 
	 * @param activationDate
	 * @param volumeNeeded
	 * @return list of available assets regarding the cost and the volume of each assets to select the better choices
	 * @throws AssetException 
	 */
	public List<AssetDTO> selectAsset(int volumeNeeded, List<AssetEntity> availableAssets) throws AssetException {
		if(volumeNeeded<=0)	return null;
		if(availableAssets.isEmpty()) {
			throw new AssetException(AssetExceptionEnum.ERROR_NO_ASSET_AVAILABLE.getMessage());
		}
		
		List<AssetDTO> sortedAssets = new ArrayList<AssetDTO>();
		AssetEntity bestAsset = null;
		int remainVolume = volumeNeeded;
		
		//Look for a unique asset solution first
		for (AssetEntity asset:availableAssets) {
            if (asset.getVolume() >= volumeNeeded) {
                if (bestAsset == null || asset.getActivationCost() < bestAsset.getActivationCost()) {
                	bestAsset = asset;
                }
            }
        }
		
		//Sorting the list by efficiency of each asset. cost/volume = price for 1Kw
		availableAssets.sort(Comparator.comparingDouble(assetEntity -> (double) assetEntity.getActivationCost() / assetEntity.getVolume()));
		
		//Once sorted then look for the best solution cost
		for(AssetEntity asset:availableAssets) {
			sortedAssets.add(AssetMapper.toDTO(asset));
			remainVolume-=asset.getVolume();
			if(remainVolume<=0) {
				//check what is the best cost solution
				if(bestAsset!=null && bestAsset.getActivationCost()<sortedAssets.stream().mapToDouble(AssetDTO::getCost).sum()) {
					List<AssetDTO> uniqueAsset = new ArrayList<AssetDTO>();
					uniqueAsset.add(AssetMapper.toDTO(bestAsset));
					return uniqueAsset;
				}else {
					return sortedAssets;
				}
			}
		}
		
		if(remainVolume>0) {
			throw new AssetException(AssetExceptionEnum.ERROR_VOLUME_TOO_BIG.getMessage());
		}else
			throw new AssetException(AssetExceptionEnum.ERROR_NO_ASSET_SELECTED.getMessage());
	}
}
