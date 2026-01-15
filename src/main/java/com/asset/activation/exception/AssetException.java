package com.asset.activation.exception;

@SuppressWarnings("serial")
public class AssetException extends Exception {
	public AssetException(String message) {
		super("Asset exception:" + message);
	}
}
