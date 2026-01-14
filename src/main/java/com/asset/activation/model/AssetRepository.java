package com.asset.activation.model;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@Repository
public class AssetRepository {

	private final List<AssetEntity> assets;
	private String filePath = "src\\main\\resources\\static\\assets.json";
	
	public AssetRepository() {
		this.assets = loadAssetsFromJson();
	}
		
	/**
	 * Method to load the json fill which simulate the whole list of Assets
	 * @return List<AssetEntity> list of json entities
	 */
	public List<AssetEntity> loadAssetsFromJson() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
			List<AssetEntity> assets = mapper.readValue(new File(filePath), new TypeReference<List<AssetEntity>>() {});
			
			return assets;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Method to get the list of available assets
	 * @param activationDate
	 * @param volumeNeeded
	 * @return
	 */
	public List<AssetDTO> findAvailableAssets(LocalDateTime activationDate, int volumeNeeded){
		List<AssetDTO> availableAssets = new ArrayList<AssetDTO>();
		
		for(AssetEntity asset:assets) {
			for (Availability availabilitySlot : asset.getAvailability()) {
                if (!activationDate.isBefore(availabilitySlot.getStart()) &&
                    !activationDate.isAfter(availabilitySlot.getEnd())) {
                	availableAssets.add(AssetMapper.toDTO(asset));
                	volumeNeeded=volumeNeeded-asset.getVolume();
                	if(volumeNeeded<0)return availableAssets;
                }
            }
		}
		
		return availableAssets;
	}

	/**
	 * Method to get the list of available assets with more parameters and complexity
	 * @param activationDate
	 * @param volumeNeeded
	 * @return
	 */
	public List<AssetDTO> findAvailableAssetsComplex(LocalDateTime activationDate, int volumeNeeded) {
		List<AssetDTO> availableAssets = new ArrayList<AssetDTO>();
		//TODO
		
		return availableAssets;
	}
}
