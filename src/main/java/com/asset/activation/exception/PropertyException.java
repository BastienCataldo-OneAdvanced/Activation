package com.asset.activation.exception;

@SuppressWarnings("serial")
public class PropertyException extends Exception {
	public PropertyException(String message) {
		super("Property exception:" + message);
	}
}
