package com.asset.activation.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.asset.activation.data.AssetRepository;
import com.asset.activation.factory.AssetTestCaseFactory;

public class AssetSelectionServiceTest {
	
	@Mock
	private AssetRepository assetRepo;
	private AssetSelectionService assetService;
	private AssetTestCaseFactory factory;
	 
	@BeforeEach
	void init() {
		assetService = new AssetSelectionService(assetRepo);
	}
	
	@Test
	void testWithEmptyAssetList() {
		
	}
	
	@Test
	void testWithNoDateMatchOfAsset() {
		
	}
	
	@Test
	void testWithNotEnouthVolume() {
		
	}
	
	@Test
	void testWithOneMatchOfAsset() {
		
	}
}
