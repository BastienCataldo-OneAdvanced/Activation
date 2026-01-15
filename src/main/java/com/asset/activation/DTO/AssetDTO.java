package com.asset.activation.DTO;

public class AssetDTO {
	private String code;
	private String name;
	private int activatedVolume;
	private double cost;
	
	public AssetDTO(String codeEntity, String nameEntity, int volumeEntity, double activationCostEntity) {
		code = codeEntity;
		name = nameEntity;
		activatedVolume = volumeEntity;
		cost = activationCostEntity;
	}
	
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
	public int getActivatedVolume() {
		return activatedVolume;
	}
	public void setActivatedVolume(int activatedVolume) {
		this.activatedVolume = activatedVolume;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
}
