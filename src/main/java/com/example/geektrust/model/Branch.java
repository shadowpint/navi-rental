package com.example.geektrust.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "branch")
public class Branch implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "city")
	private String city;

	@Column(name = "name")
	private String name;

	@Column(name = "lat")
	private Double lat;

	@Column(name = "lon")
	private Double lon;

	@Column(name = "address")
	private String address;

	@Column(name = "available_vehicles")
	private String availableVehicles;



	public Branch(String branchName, String availableVehicles) {
		this.name = branchName;
		this.availableVehicles = availableVehicles;
	}

	public Branch(String name) {
		this.name = name;
	}

	public Branch() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<String> getAvailableVehicles() {
		return Arrays.asList(availableVehicles.split(","));
	}

	public void setAvailableVehicles(String availableVehicles) {
		this.availableVehicles = availableVehicles;
	}


}
