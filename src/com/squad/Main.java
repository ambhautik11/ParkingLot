package com.squad;

import com.squad.exception.ParkingLotFullException;
import com.squad.exception.ParkingLotVacantException;
import com.squad.model.ParkingTicket;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    /**
     * responsible for user input and output as well validations
     *
     * @param args input file which consists of supported commands with
     */
    public static void main(String[] args) {
        File file =
                new File(args[0]);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file at specified location");
            return;
        }
        ParkingManager parkingManager = null;
        ParkingQueryHelper parkingQueryHelper = null;
        while (sc.hasNextLine()) {
            String cmd = sc.nextLine();
            String[] cmdArgs = cmd.split(" ");

            if (parkingManager == null && !"Create_parking_lot".equals(cmdArgs[0])) {
                System.out.println("Please initialize parking manager first with cmd 'Create_parking_lot'");
                return;
            }
            switch (cmdArgs[0]) {
                case "Create_parking_lot":
                    parkingManager = new ParkingManager(Integer.parseInt(cmdArgs[1]));
                    parkingQueryHelper = new ParkingQueryHelper();
                    System.out.println(String.format("Created parking of %s slots", parkingManager.getTotalParkingSlot()));
                    break;
                case "Park":
                    ParkingTicket parkingTicket = new ParkingTicket(cmdArgs[1], Integer.parseInt(cmdArgs[3]));
                    try {
                        parkingTicket.setAllottedSlot(parkingManager.park(parkingTicket));
                        parkingQueryHelper.add(parkingTicket);
                    } catch (ParkingLotFullException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println(String.format("Car with vehicle registration number \"%s\" has been parked at slot number %d", parkingTicket.getRegNo(), parkingTicket.getAllottedSlot()));
                    break;
                case "Slot_numbers_for_driver_of_age":
                    System.out.println(parkingQueryHelper.getSlots(Integer.parseInt(cmdArgs[1])).stream().map(String::valueOf)
                            .collect(Collectors.joining(",")));
                    break;
                case "Slot_number_for_car_with_number":
                    System.out.println(parkingQueryHelper.getSlot(cmdArgs[1]));
                    break;
                case "Vehicle_registration_number_for_driver_of_age":
                    System.out.println(parkingQueryHelper.getRegNos(Integer.parseInt(cmdArgs[1])).stream().collect(Collectors.joining(",")));
                    break;
                case "Leave":
                    try {
                        parkingTicket = parkingManager.leave(Integer.parseInt(cmdArgs[1]));
                        System.out.println(String.format("Slot number %d vacated, the car with vehicle registration number \"%s\" left the space, the driver of the car was of age %d", parkingTicket.getAllottedSlot(), parkingTicket.getRegNo(), parkingTicket.getAge()));

                    } catch (ParkingLotVacantException e) {
                        System.out.println("Slot already vacant");
                    }

                    break;
                default:
                    System.out.println(String.format("cmd '%s' not supported", cmdArgs[0]));

            }
        }
    }
}
