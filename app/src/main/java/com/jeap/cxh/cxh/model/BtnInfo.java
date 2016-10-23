package com.jeap.cxh.cxh.model;

/**
 * Created by laed on 23/10/2016.
 */

public class BtnInfo {
    public String btnName;
    public String btnSet;
    public int btnId;

    public BtnInfo(){
        super();
    }

    public BtnInfo(String btnSet, String btnName, int btnId ){
        super();
        this.btnName = btnName;
        this.btnSet = btnSet;
        this.btnId = btnId;
    }

    public String getName(){
        return this.btnName;
    }

    public String getSet(){
        return this.btnSet;
    }

    public int getId(){
        return this.btnId;
    }



}

