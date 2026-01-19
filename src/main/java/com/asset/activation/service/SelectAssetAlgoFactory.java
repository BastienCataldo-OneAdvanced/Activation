package com.asset.activation.service;

import com.asset.activation.exception.PropertyException;

public class SelectAssetAlgoFactory {
	public static SelectAssetAlgo getAssetAlgo(String algoType) throws PropertyException {
		switch(algoType) {
			case "simple":
				return new SelectAssetSimple();
			case "complex":
				return new SelectAssetComplex();
			default:
				throw new PropertyException("no type of algo selected");
		}
	}
}
