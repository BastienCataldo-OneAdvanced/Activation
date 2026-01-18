package com.asset.activation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asset.activation.DTO.AssetDTO;
import com.asset.activation.form.ActivationRequest;
import com.asset.activation.service.AssetSelectionService;

@RestController
public class ActivationController {

	private final AssetSelectionService assetSelectionService;
	
	public ActivationController(AssetSelectionService assetSelectionService) {
	    this.assetSelectionService = assetSelectionService;
	}
	
	@PostMapping("/flexcity/activate")
	public List<AssetDTO> activate(@RequestBody ActivationRequest request) {
	   return assetSelectionService.selectAssets(request);
	}
}
