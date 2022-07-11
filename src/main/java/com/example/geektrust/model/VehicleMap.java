package com.example.geektrust.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vehicle_map")
public class VehicleMap implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "branch_id", referencedColumnName = "id")
	Branch branch;

	@ManyToOne
	@JoinColumn(name = "vehicle_id", referencedColumnName = "id")
	Vehicle vehicle;

	@Column(name = "rent_price")
	private Double rentPrice;

	@Column(name = "quantity")
	private Long quantity;

	@Column(name = "available")
	private Boolean available;





	public VehicleMap() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Double getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(Double rentPrice) {
		this.rentPrice = rentPrice;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
}
