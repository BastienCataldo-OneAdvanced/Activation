package com.asset.activation.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.asset.activation.DTO.AssetDTO;
import com.asset.activation.DTO.AssetMapper;
import com.asset.activation.data.AssetEntity;
import com.asset.activation.data.AssetRepository;
import com.asset.activation.data.AvailabilityEntity;
import com.asset.activation.exception.AssetException;
import com.asset.activation.exception.PropertyException;
import com.asset.activation.form.ActivationRequest;

@Service
public class AssetSelectionService {
	
	private final AssetRepository repo;
	
	@Value("${activation.type.algo}")
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
					return findAvailableAssets(request.getDate(), request.getVolume());
				case "complex":
					return findAvailableAssetsComplex(request.getDate(), request.getVolume());
				default:
					throw new PropertyException("no type of algo selected");
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Method to get the list of available assets through a simple way with date and volume conditions
	 * @param activationDate
	 * @param volumeNeeded
	 * @return list of available assets regarding the simple conditions
	 * @throws AssetException 
	 */
	public List<AssetDTO> findAvailableAssets(LocalDateTime activationDate, int volumeNeeded) throws AssetException{
		List<AssetDTO> availableAssets = new ArrayList<AssetDTO>();
		
		for(AssetEntity asset:repo.getAssets()) {
			for (AvailabilityEntity availabilitySlot : asset.getAvailability()) {
                if (!activationDate.isBefore(availabilitySlot.getStart()) &&
                    !activationDate.isAfter(availabilitySlot.getEnd())) {
                	availableAssets.add(AssetMapper.toDTO(asset));
                	volumeNeeded=volumeNeeded-asset.getVolume();
                	if(volumeNeeded<0)return availableAssets;
                }
            }
		}
		if(availableAssets.isEmpty())
			throw new AssetException("No assets found!");
		return availableAssets;
	}

	/**
	 * Method to get the list of available assets with more parameters and complexity
	 * @param activationDate
	 * @param volumeNeeded
	 * @return list of available assets regarding the simple conditions
	 */
	public List<AssetDTO> findAvailableAssetsComplex(LocalDateTime activationDate, int volumeNeeded) {
		List<AssetDTO> availableAssets = new ArrayList<AssetDTO>();
		//TODO
		
		return availableAssets;
	}

}
