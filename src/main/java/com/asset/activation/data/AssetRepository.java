package com.asset.activation.data;

import java.io.File;
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

	public List<AssetEntity> getAssets() {
		return assets;
	}
}
