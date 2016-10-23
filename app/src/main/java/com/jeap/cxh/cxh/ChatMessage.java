package com.jeap.cxh.cxh;


import com.jeap.cxh.cxh.model.FlightInfo;

import java.util.List;

public class ChatMessage {
	public boolean left;
	public int msgType;
	public String message;
    public FlightInfo flightInfo;
    public List listObj;


    public ChatMessage(boolean left, int msgType, String message) {
        super();
        this.left = left;
        this.msgType = msgType;
        this.message = message;

    }

    public ChatMessage(boolean left, int msgType, FlightInfo flightInfo) {
        super();
        this.left = left;
        this.msgType = msgType;
        this.flightInfo = flightInfo;

    }

    public ChatMessage(boolean left, int msgType, List listObj) {
        super();
        this.left = left;
        this.msgType = msgType;
        this.listObj = listObj;

    }

}