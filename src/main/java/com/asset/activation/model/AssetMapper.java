package com.asset.activation.model;

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
