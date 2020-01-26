package com.squad.exception;

/**
 * When cars try to leave slot that is already vacant
 */
public class ParkingLotVacantException extends Exception {

    public ParkingLotVacantException(String message) {
        super(message);
    }
}
