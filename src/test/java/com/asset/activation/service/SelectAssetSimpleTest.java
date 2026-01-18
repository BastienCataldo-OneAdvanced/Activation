package com.asset.activation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.asset.activation.DTO.AssetDTO;
import com.asset.activation.data.AssetEntity;
import com.asset.activation.exception.AssetException;
import com.asset.activation.factory.AssetFactory;

public class SelectAssetSimpleTest {

	private List<AssetEntity> availableAssets;
	private AssetFactory assetFactory = new AssetFactory();
	private AssetEntity asset1;
	private AssetEntity asset2;
	private AssetEntity asset3;
	 
	@BeforeEach
	void init() {
		availableAssets = new ArrayList<>();
		//Illustrate asset creation without mockito
		//AssetEntity asset1 = assetFactory.createAsset("asset1", "orange", assetType.CONSUMER.toString(), 1000, new ArrayList<AvailabilityEntity>(), 100);
		
		asset1 = mock(AssetEntity.class);
		when(asset1.getVolume()).thenReturn(100);
		asset2 = mock(AssetEntity.class);
		when(asset2.getVolume()).thenReturn(150);
		asset3 = mock(AssetEntity.class);
		when(asset3.getVolume()).thenReturn(200);
	}
	/********* Nominal tests *********/
	
	
	@Test
	@DisplayName("Test with only 1 asset")
	void testWithOneMatchOfAsset() throws AssetException {
		availableAssets.add(asset1);
        
		List<AssetDTO> result = SelectAssetSimple.selectAssetsSimple(100, availableAssets);
        
        assertNotNull(result);
        assertEquals(2, result.size());
	}
	
	@Test
    @DisplayName("Test when volume matches exactly")
    void testExactVolume() throws AssetException {
        availableAssets.add(asset1);
        availableAssets.add(asset2);
        
        List<AssetDTO> result = SelectAssetSimple.selectAssetsSimple(250, availableAssets);
        
        assertNotNull(result);
        assertEquals(2, result.size());
    }
	
	
	/********* Error tests *********/
	
	@Test
	@DisplayName("Test when no assets available")
    void testNoAssetsAvailable() {
		AssetException exception = assertThrows(AssetException.class, () -> {SelectAssetSimple.selectAssetsSimple(100, new ArrayList<AssetEntity>());});
        
        assertEquals("No assets found!", exception.getMessage());
    }
	

	@Test
	@DisplayName("")
	void testWithNotEnouthVolume() {
		
	}
	
	
}
