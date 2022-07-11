package com.example.geektrust;

import com.example.geektrust.controller.BookingController;


public class GeekTrust {
    public static void main(String[] args) {
        org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
        BookingController bookingController = new BookingController();
        bookingController.run(args[0]);

    }
}
