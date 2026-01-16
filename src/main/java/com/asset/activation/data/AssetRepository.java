package com.asset.activation.data;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@Repository
public class AssetRepository {

	private final List<AssetEntity> assets;
	private final String filePath;
	
	public AssetRepository(@Value("${activation.assets.json.path}")String filePath) {
		this.filePath = filePath;
		this.assets = loadAssetsFromJson();
	}
		
	/**
	 * Method to load the json fill which simulate the whole list of Assets
	 * @return List<AssetEntity> list of json entities mapped into assetEntity object
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
	 * Method to get the list of available assets regarding the Date
	 * @param activationDate
	 * @return list of available assets
	 */
	public List<AssetEntity> findAssetsByDate(LocalDateTime activationDate) {
		List<AssetEntity> availableAssets = new ArrayList<AssetEntity>();
		for(AssetEntity asset:assets) {
			for (AvailabilityEntity availabilitySlot : asset.getAvailability()) {
                if (!activationDate.isBefore(availabilitySlot.getStart()) &&
                    !activationDate.isAfter(availabilitySlot.getEnd())) {
                	availableAssets.add(asset);
                	break;//Don't check other hours if we already got 1 because some asset have several hours available in the same day
                }
            }
		}

		return availableAssets;
	}

	public List<AssetEntity> getAssets() {
		return assets;
	}
}
