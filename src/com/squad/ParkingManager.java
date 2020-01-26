package com.squad;


import com.squad.exception.ParkingLotFullException;
import com.squad.exception.ParkingLotVacantException;
import com.squad.model.ParkingTicket;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * responsible for managing parking slots with add and leave methods
 *
 */
public class ParkingManager {
    private int totalParkingSlot;
    private Map<Integer, ParkingTicket> ticketMap;
    private PriorityQueue<Integer> availableQueue;

    /**
     * initializes the parking lot with fix size
     * @param parkingSlot number of parking slot in parking lot
     */
    public ParkingManager(int parkingSlot) {
        this.totalParkingSlot = parkingSlot;
        ticketMap = new HashMap<Integer, ParkingTicket>();
        availableQueue = new PriorityQueue<Integer>();
        for (int i = 1; i <= parkingSlot; i++)
            availableQueue.add(i);
    }

    /**
     * this method assigns parking slot which is nearest to enrty to ticket.
     * @param ticket ParkingTicket to which slot need to be assigned
     * @return slot number of allotted slot
     * @throws ParkingLotFullException if there is no vacant parking slot left
     */
    public int park(ParkingTicket ticket) throws ParkingLotFullException {
        if(availableQueue.size()==0)
            throw new ParkingLotFullException("Parking lot is full.");
        int nextAvailableSlot = availableQueue.poll();
        ticketMap.put(nextAvailableSlot,ticket);

        return nextAvailableSlot;
    }

    /**
     * mark the parking slot available
     * @param slot
     * @return the ticket assigned to slot
     * @throws ParkingLotVacantException if slot is already vacant
     */
    public ParkingTicket leave(int slot) throws ParkingLotVacantException {
        if(!ticketMap.containsKey(slot))
            throw new ParkingLotVacantException("Slot already vacant");
        ParkingTicket ticket = ticketMap.remove(slot);
        availableQueue.add(slot);
        return ticket;
    }

    public int getTotalParkingSlot() {
        return totalParkingSlot;
    }
}
