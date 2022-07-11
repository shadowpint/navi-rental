package com.example.geektrust.service;

import com.example.geektrust.dao.BranchDao;
import com.example.geektrust.model.Branch;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExecuteServiceImplTest {
    BranchDao branchDao = new BranchDao();
    ExecuteService executeService = new ExecuteServiceImpl();
    @BeforeEach
    void setUp() {

        Branch branch = new Branch("B1","CAR,BIKE,VAN");
        branchDao.saveBranch(branch);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addBranch() {
        String name = "B1";
        Branch found = branchDao.getBranchByName(name);
        assertEquals(found.getName(), name);
    }

    @Test
    void addVehicle() {
        Boolean addVehicleStatus =executeService.addVehicle("B1 BIKE V3 300");
        assertEquals(addVehicleStatus, true);
    }

    @Test
    void viewAvailableVehicles() {
        executeService.addBooking("B1 BIKE 2 3 9696666763 Neeraj");
        String vehicles=executeService.viewAvailableVehicles("B1 1 5");
        assertEquals(vehicles, "");
    }

    @Test
    void addBooking() {
        Double price=executeService.addBooking("B1 BIKE 2 3 9696666763 Neeraj");
        assertEquals(price, -1.0);
    }


}