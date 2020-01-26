package com.squad.exception;

/**
 * used when parking lot is full and there is no vacant slot that can be assigned to car any more.
 */
public class ParkingLotFullException extends Exception {


    public ParkingLotFullException(String message) {
        super(message);
    }

}
