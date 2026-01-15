package com.asset.activation.form;

import java.time.LocalDateTime;

public class ActivationRequest {
	private LocalDateTime date;
	private int volume;
	
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public int getVolume() {
		return volume;
	}
	
	public void setVolume(int volume) {
		this.volume = volume;
	}
}
