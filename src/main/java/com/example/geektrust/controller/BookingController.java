package com.example.geektrust.controller;



import com.example.geektrust.service.ExecuteService;
import com.example.geektrust.service.ExecuteServiceImpl;

import java.util.Arrays;
import java.util.List;

public class BookingController {

	ExecuteService executeService = new ExecuteServiceImpl();

	public void run(String args) {
		List<String[]> choices = executeService.executingOptions(args);
		for (String[] choice : choices) {
			switch (choice[0]) {
				case "ADD_BRANCH":
					String branchData[]=choice[1].split(" ");
					String branchName=branchData[0];
				    Boolean addBranchStatus= executeService.addBranch(branchName, Arrays.asList(branchData[1].split("\\s*,\\s*")));
					System.out.println(String.valueOf(addBranchStatus).toUpperCase());
					break;
				case "ADD_VEHICLE":
				Boolean addVehicleStatus =executeService.addVehicle(choice[1]);
				System.out.println(String.valueOf(addVehicleStatus).toUpperCase());
					break;
				case "BOOK":
					Double price=executeService.addBooking(choice[1]);
					System.out.println(price.intValue());
					break;
				case "DISPLAY_VEHICLES":
					String vehicles=executeService.viewAvailableVehicles(choice[1]);
					System.out.println(vehicles);
					break;
			case "EXIT":
				System.exit(0);
				break;
			}
		}
	}
}
