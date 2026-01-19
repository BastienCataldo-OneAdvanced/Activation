package com.asset.activation.data;

public enum AssetExceptionEnum {
	
	ERROR_NO_ASSET_AVAILABLE("No assets found!"),
	ERROR_VOLUME_TOO_BIG("The assets cant answer to the requested volume!");
	
	
	private final String message;

	AssetExceptionEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
