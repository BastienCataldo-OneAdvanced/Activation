package com.asset.activation.data;

import java.util.List;

public class AssetEntity {
	private String code;
	private String name;
	private double activationCost;
	private List<AvailabilityEntity> availability;
	private int volume;
	//private Duration duree; Is there a duration information?
	//private int priority;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getActivationCost() {
		return activationCost;
	}
	public void setActivationCost(double activationCost) {
		this.activationCost = activationCost;
	}

	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public List<AvailabilityEntity> getAvailability() {
		return availability;
	}
	public void setAvailability(List<AvailabilityEntity> availability) {
		this.availability = availability;
	}
}
