package com.asset.activation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.asset.activation.DTO.AssetDTO;
import com.asset.activation.data.AssetRepository;
import com.asset.activation.form.ActivationRequest;

@Service
public class AssetSelectionService {
	
	private final AssetRepository repo;
	
	@Value("${activation.assets.selection.algo.type}")
	private String algoType;

    public AssetSelectionService(AssetRepository repo) {
    	this.repo = repo;
    }
	
    /**
     * Method to select the requested assets from the available list of assets
     * @param request
     * @return selectedAsset list
     */
	public List<AssetDTO> activateAssets(ActivationRequest request){
		try {
			//Ask to the factory what algo class choose regarding the algoType
			SelectAssetAlgo selectAssetAglo =  SelectAssetAlgoFactory.getAssetAlgo(algoType);
			//Then call the algorithm method and return the selected list of asset mapped in the DTO object
			return selectAssetAglo.selectAsset(request.getVolume(), repo.findAssetsByDate(request.getDate()));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
