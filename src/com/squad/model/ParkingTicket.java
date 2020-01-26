package com.squad.model;

/**
 * model class of parking ticket
 *
 */
public class ParkingTicket {
    private int allottedSlot;
    private String regNo;
    private int age;

    public ParkingTicket(String regNo, int age) {
        this.regNo = regNo;
        this.age = age;
    }

    public String getRegNo() {
        return regNo;
    }

    public int getAge() {
        return age;
    }

    public int getAllottedSlot() {
        return allottedSlot;
    }

    public void setAllottedSlot(int allottedSlot) {
        this.allottedSlot = allottedSlot;
    }

    @Override
    public String toString() {
        return "ParkingTicket{" +
                "regNo='" + regNo + '\'' +
                ", age=" + age +
                '}';
    }
}
