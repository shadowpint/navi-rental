package com.example.geektrust.service;


import com.example.geektrust.dao.*;
import com.example.geektrust.model.*;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ExecuteServiceImpl implements ExecuteService {


    BranchDao branchDao = new BranchDao();
    VehicleDao vehicleDao = new VehicleDao();
    VehicleMapDao vehicleMapDao = new VehicleMapDao();
    VehicleBookingDao vehicleBookingDao = new VehicleBookingDao();
    CustomerDao customerDao = new CustomerDao();

    @Override
    public void load() {

    }


    @Override
    public List<String[]> executingOptions(String args) {
        List<String[]> choices = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(args);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                choices.add(sc.nextLine().split(" ", 2));
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return choices;
    }

    @Override
    public Boolean addBranch(String branchName, List<String> vehicleTypes) {
        Branch branch = branchDao.getBranchByName(branchName);
        if (branch == null) {
            branch = new Branch(branchName, String.join(",", vehicleTypes));
            branchDao.saveBranch(branch);
            return true;
        }

        return false;
    }

    @Override
    public Boolean addVehicle(String data) {
        String vehicleData[] = data.split(" ");
        String branchName = vehicleData[0];

        String vehicleType = vehicleData[1];
        String vehicleId = vehicleData[2];
        Double price = Double.parseDouble(vehicleData[3]);

        Branch branch = branchDao.getBranchByName(branchName);
        if (branch != null && branch.getAvailableVehicles().contains(vehicleType)) {
            Vehicle vehicle = vehicleDao.getVehicleByName(vehicleId);
            if (vehicle == null) {
                vehicle = new Vehicle();
                vehicle.setVehicleId(vehicleId);
                vehicle.setType(vehicleType);
                vehicleDao.saveVehicle(vehicle);
            }
            VehicleMap vehicleMap = vehicleMapDao.getVehicleMap(branch.getName(), vehicle.getVehicleId());
            if (vehicleMap == null) {
                vehicleMap = new VehicleMap();
                vehicleMap.setVehicle(vehicle);
                vehicleMap.setBranch(branch);
                vehicleMap.setQuantity(1L);
                vehicleMap.setRentPrice(price);
                vehicleMap.setAvailable(true);
            } else {
                Long quantity = vehicleMap.getQuantity();
                vehicleMap.setQuantity(quantity + 1L);
            }
            vehicleMapDao.saveVehicleMap(vehicleMap);
            return true;

        }
        return false;
    }

    @Override
    public Double addBooking(String data) {
        String bookingData[] = data.split(" ");
        String branchName = bookingData[0];
        String vehicleType = bookingData[1];
        Long startTime = Long.parseLong(bookingData[2]);
        Long endTime = Long.parseLong(bookingData[3]);
        String mobileNumber = bookingData[4];
        String firstName = bookingData[5];
        Branch branch = branchDao.getBranchByName(branchName);
        Customer customer = customerDao.getCreateCustomer(mobileNumber, firstName);
        if (customer == null) {
            customer = new Customer();
            customer.setMobileNumber(mobileNumber);
            customer.setFirstName(firstName);
            customerDao.saveCustomer(customer);
        }
        VehicleMap vehicleMap = null;
        VehicleBooking oldBooking = vehicleBookingDao.getVehicleBookingByBranchAndVehicleType(branch.getId(), vehicleType);
        vehicleMap = (oldBooking != null && oldBooking.getEndTime() < startTime) ? oldBooking.getVehicleMap() : vehicleMapDao.getVehicleMapByVehicleType(branchName, vehicleType);

        if (vehicleMap != null) {
            VehicleBooking booking = new VehicleBooking();
            booking.setCustomer(customer);
            booking.setQuantity(1L);
            booking.setStartTime(startTime);
            booking.setEndTime(endTime);
            booking.setRentPrice(vehicleMap.getRentPrice());
            vehicleMap.setAvailable(false);
            vehicleMapDao.updateVehicleMap(vehicleMap);
            booking.setVehicleMap(vehicleMap);
            vehicleBookingDao.saveVehicleBooking(booking);
            return booking.getRentPrice();


        }
        return -1.0;
    }

    @Override
    public String viewAvailableVehicles(String data) {
        String[] branchData = data.split(" ");
        String branchName = branchData[0];
        Long startTime = Long.parseLong(branchData[1]);
        Long endTime = Long.parseLong(branchData[2]);
        List<VehicleMap> vehicleMapList = vehicleMapDao.getVehicleMapByAvailability(branchName, startTime, endTime);
        List<String> vehicleIds = new ArrayList<>();
        for (VehicleMap vehicleMap : vehicleMapList) {
            vehicleIds.add(vehicleMap.getVehicle().getVehicleId());
        }
        if (!vehicleIds.isEmpty()) return String.join(",", vehicleIds);
        else return "";
    }

    @Override
    public void saveAndExit() {
//		if (bookService.save()) {
//			System.out.println("\nLibrary saved.");
//			System.exit(0);
//		} else
//			System.out.println("Sorry, Something has error while saving.");
    }


}
