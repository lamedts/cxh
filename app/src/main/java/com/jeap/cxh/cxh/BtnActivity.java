package com.jeap.cxh.cxh;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by laed on 23/10/2016.
 */

public class BtnActivity extends AppCompatActivity {

    public int btnId;


    ChatActivity mainRefrence;
    BtnActivity(ChatActivity main){
        mainRefrence = main;
    }


    public void act(){
        mainRefrence.chatArrayAdapter.add(new ChatMessage(true, 0, "msg"));
    }

}
