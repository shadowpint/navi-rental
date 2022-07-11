package com.example.geektrust.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vehicle_booking")
public class VehicleBooking implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;


	@ManyToOne
	@JoinColumn(name = "vehicle_map_id", referencedColumnName = "id")
	VehicleMap vehicleMap;

	@Column(name = "rent_price")
	private Double rentPrice;

	@Column(name = "quantity")
	private Long quantity;


	@Column(name = "start_time")
	private Long startTime;


	@Column(name = "end_time")
	private Long endTime;

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	Customer customer;



	public VehicleBooking() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public VehicleMap getVehicleMap() {
		return vehicleMap;
	}

	public void setVehicleMap(VehicleMap vehicleMap) {
		this.vehicleMap = vehicleMap;
	}

	public Double getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(Double rentPrice) {
		this.rentPrice = rentPrice*(this.endTime-this.startTime);
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
