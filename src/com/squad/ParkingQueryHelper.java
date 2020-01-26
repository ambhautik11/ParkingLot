package com.squad;

import com.squad.model.ParkingTicket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * helper class to support query operations on parking lot
 */
public class ParkingQueryHelper {
    private Map<Integer,List<ParkingTicket>> ageTicketMap;
    private Map<String,ParkingTicket> regNoTicketMap;

    public ParkingQueryHelper() {
        ageTicketMap = new HashMap<Integer, List<ParkingTicket>>();
        regNoTicketMap = new HashMap<String, ParkingTicket>();
    }

    /**
     * updated query maps when there's new entry in parking lot
     * @param ticket
     */
    public void add(ParkingTicket ticket){
        List<ParkingTicket> ticketList = ageTicketMap.getOrDefault(ticket.getAge(),new ArrayList<ParkingTicket>());
        ticketList.add(ticket);
        ageTicketMap.put(ticket.getAge(),ticketList);
        regNoTicketMap.put(ticket.getRegNo(),ticket);
    }

    /**
     * updated query maps when someone leaves parking lot
     * @param ticket
     */
    public void remove(ParkingTicket ticket){
        List<ParkingTicket> ticketList = ageTicketMap.getOrDefault(ticket.getAge(),new ArrayList<ParkingTicket>());
        ticketList.remove(ticket);
        ageTicketMap.put(ticket.getAge(),ticketList);
        regNoTicketMap.remove(ticket.getRegNo());
    }

    /**
     *
     * @param regNo registration no of cars
     * @return slot number of cars with regNo
     */
    public int getSlot(String regNo){
        return regNoTicketMap.get(regNo).getAllottedSlot();
    }

    /**
     *
     * @param age age of driver
     * @return list of registration no of cars of all diver with given age
     */
    public List<String> getRegNos(int age){
        if(!ageTicketMap.containsKey(age))
            return new ArrayList<>();
        return ageTicketMap.get(age).stream().map(ParkingTicket::getRegNo).collect(Collectors.toList());
    }

    /**
     *
     * @param age age of driver
     * @return list of slot no of cars of all diver with given age
     */
    public List<Integer> getSlots(int age){
        if(!ageTicketMap.containsKey(age))
            return new ArrayList<>();
        return ageTicketMap.get(age).stream().map(ParkingTicket::getAllottedSlot).collect(Collectors.toList());
    }



}
