package com.jeap.cxh.cxh.model;

/**
 * Created by laed on 22/10/2016.
 */

public class FlightInfo {
    public String flightNo;
    public String gate;
    public String seat;
    public String arrival;
    public String departure;
    public String time;

    public FlightInfo(){
        super();
    }

    public FlightInfo(String flightNo, String gate, String seat, String arrival, String departure, String time) {
        super();
        this.flightNo = flightNo;
        this.gate = gate;
        this.seat = seat;
        this.arrival = arrival;
        this.time = time;
        this.departure = departure;
    }

    public void setInfo(){
        this.flightNo = "CX105";
        this.gate = "70";
        this.seat = "68C";
        this.arrival = "MEL";
        this.time = "2330";
        this.departure = "HKG";
    }

    public String getGate(){
        return this.gate;
    }

    public String getFlightNo(){
        return this.flightNo;
    }

    public String getSeat(){
        return this.seat;
    }

    public String getArrival(){
        return this.arrival;
    }

    public String getDeparture(){
        return this.departure;
    }

    public String getTime(){
        return this.time;
    }
}