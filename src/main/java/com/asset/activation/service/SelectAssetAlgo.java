package com.asset.activation.service;

import java.util.List;

import com.asset.activation.DTO.AssetDTO;
import com.asset.activation.data.AssetEntity;
import com.asset.activation.exception.AssetException;

public interface SelectAssetAlgo {
	List<AssetDTO> selectAsset(int volumeNeeded, List<AssetEntity> availableAssets)throws AssetException;
}
