package com.asset.activation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.asset.activation.DTO.AssetDTO;
import com.asset.activation.data.AssetRepository;
import com.asset.activation.exception.PropertyException;
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
     * @throws PropertyException 
     */
	public List<AssetDTO> selectAssets(ActivationRequest request){
		try {
			switch(algoType) {
				case "simple":
					return SelectAssetSimple.selectAssetsSimple(request.getVolume(), repo.findAssetsByDate(request.getDate()));
				case "complex":
					return SelectAssetComplex.selectAssetsComplex(request.getVolume(), repo.findAssetsByDate(request.getDate()));
				default:
					throw new PropertyException("no type of algo selected");
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	

	
}
