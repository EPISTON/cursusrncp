package com.courtalon.jpa_supermapform.beans;

public class Localisation {
	private double longitude;
	private double latitude;
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Localisation() { this(0,0); } 
	public Localisation(double longitude, double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "Localisation [longitude=" + longitude + ", latitude=" + latitude + "]";
	}
	
}
