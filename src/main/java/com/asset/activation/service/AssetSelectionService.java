package com.asset.activation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.asset.activation.model.ActivationRequest;
import com.asset.activation.model.AssetDTO;
import com.asset.activation.model.AssetRepository;

@Service
public class AssetSelectionService {
	
	private final AssetRepository repo;
	@Value("${activation.complexe}")
	private boolean complexity;

    public AssetSelectionService(AssetRepository repo) {
        this.repo = repo;
    }
	
    /**
     * Method to select the requested assets from the available list of assets
     * @param request
     * @return selectedAsset list
     */
	public List<AssetDTO> selectAssets(ActivationRequest request) {
		if(complexity) {
			return repo.findAvailableAssetsComplex(request.getDate(), request.getVolume());
		}else {
			return repo.findAvailableAssets(request.getDate(), request.getVolume());
		}
	}

}
