package com.asset.activation.DTO;

import com.asset.activation.data.AssetEntity;

public class AssetMapper {
	 public static AssetDTO toDTO(AssetEntity assetEntity) {
	        if (assetEntity == null) return null;

	        return new AssetDTO(
	        		assetEntity.getCode(),
	        		assetEntity.getName(),
	        		assetEntity.getVolume(),
	        		assetEntity.getActivationCost()
	        );
	    }
}
