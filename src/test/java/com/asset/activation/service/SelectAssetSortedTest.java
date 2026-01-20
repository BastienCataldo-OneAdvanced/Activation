package com.asset.activation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import com.asset.activation.data.AssetExceptionEnum;
import com.asset.activation.exception.AssetException;

public class SelectAssetSortedTest {
	private List<AssetEntity> availableAssets;
	private AssetEntity asset1;
	private AssetEntity asset2;
	private AssetEntity asset3;
	private SelectAssetSorted selectAssetSorted = new SelectAssetSorted();

	private AssetEntity createMockAsset(int volume, double activationCost) {
        AssetEntity asset = mock(AssetEntity.class);
        when(asset.getVolume()).thenReturn(volume);
        when(asset.getActivationCost()).thenReturn(activationCost);
        return asset;
    }
	
	@BeforeEach
	void init() {
		availableAssets = new ArrayList<>();

		asset1 = createMockAsset(100, 200);
		asset2 = createMockAsset(150, 200);
		asset3 = createMockAsset(200, 200);
	}
	/********* Nominal tests *********/
	
	
	@Test
	@DisplayName("Test with 1 match asset")
	void testWithOneMatchOfAsset() throws AssetException {
		availableAssets.add(asset1);
        
		List<AssetDTO> result = selectAssetSorted.selectAsset(100, availableAssets);
        
        assertNotNull(result);
        assertEquals(1, result.size());
	}
	
	@Test
    @DisplayName("Test when volume matches exactly")
    void testExactVolume() throws AssetException {
		availableAssets.add(asset1);
		availableAssets.add(asset2);
        
        List<AssetDTO> result = selectAssetSorted.selectAsset(250, availableAssets);
        
        assertNotNull(result);
        assertEquals(2, result.size());
    }
	
	@Test
    @DisplayName("Volume needed equal zero")
    void testZeroVolumeNeeded() throws AssetException {
		availableAssets.add(asset1);
        
        List<AssetDTO> result = selectAssetSorted.selectAsset(0, availableAssets);
        
        assertNull(result);
    }
	
	@Test
    @DisplayName("Volume needed equal zero")
    void testNegativeVolumeNeeded() throws AssetException {
		availableAssets.add(asset1);
        
        List<AssetDTO> result = selectAssetSorted.selectAsset(-10, availableAssets);
        
        assertNull(result);
    }
	
	@Test
	@DisplayName("check the sort of efficiency (cost/volume) with 1 best choice")
	void testAssetSortingByEfficiency() throws AssetException {
		AssetEntity assetRatio1 = createMockAsset(100, 200);
		AssetEntity assetRatio2 = createMockAsset(150, 50);
		AssetEntity assetRatio3 = createMockAsset(200, 150);
		
		List<AssetEntity> assets = new ArrayList<>(List.of(assetRatio1, assetRatio2, assetRatio3));
		
		List<AssetDTO> result = selectAssetSorted.selectAsset(200, assets);
		
		assertNotNull(result);
		// the first should be the best efficiency
		assertEquals(150, result.get(0).getCost());
	}
	
	@Test
	@DisplayName("check the sort of efficiency (cost/volume) with 2 assets")
	void testAssetSortingByEfficiency2() throws AssetException {
		AssetEntity assetRatio1 = createMockAsset(200, 150);
		AssetEntity assetRatio2 = createMockAsset(150, 50);
		AssetEntity assetRatio3 = createMockAsset(100, 50);
		
		List<AssetEntity> assets = new ArrayList<>(List.of(assetRatio1, assetRatio2, assetRatio3));
		
		List<AssetDTO> result = selectAssetSorted.selectAsset(200, assets);
		
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(50, result.get(0).getCost());
        assertEquals(50, result.get(1).getCost());
	}
	
	/********* Error tests *********/
	
	@Test
	@DisplayName("Test when no assets available")
    void testNoAssetsAvailable() {
		AssetException exception = assertThrows(AssetException.class, () -> {selectAssetSorted.selectAsset(100, new ArrayList<AssetEntity>());});
        
        assertEquals(AssetExceptionEnum.ERROR_NO_ASSET_AVAILABLE.getMessage(), exception.getMessage());
    }
	
	@Test
    @DisplayName("Test when volume is too big and not enought assets")
    void testtooBigVolume() throws AssetException {
		availableAssets.add(asset1);
        availableAssets.add(asset2);
        availableAssets.add(asset3);
        
        AssetException exception = assertThrows(AssetException.class, () -> {selectAssetSorted.selectAsset(500, availableAssets);});
        
        assertEquals(AssetExceptionEnum.ERROR_VOLUME_TOO_BIG.getMessage(), exception.getMessage());
    }
}
