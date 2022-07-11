package com.example.geektrust.service;

import java.util.List;

public interface ExecuteService {

	void load();

	List<String[]> executingOptions(String args);

	Boolean addBranch(String branchName,List<String> vehicleTypes);

	void saveAndExit();

	Boolean addVehicle(String s);

	Double addBooking(String s);

	String viewAvailableVehicles(String s);
}
