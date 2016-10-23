package com.jeap.cxh.cxh;


import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.jeap.cxh.cxh.model.BtnInfo;
import com.jeap.cxh.cxh.model.FlightInfo;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class ChatActivity extends AppCompatActivity  {

    private RecyclerView myRecyclerView;

    private LinearLayoutManager linearLayoutManager;

    private static final String TAG = "ChatActivity";

    public ChatArrayAdapter chatArrayAdapter;
    private ListView listView;
    private EditText chatText;
    private Button buttonSend;
    public FlightInfo flightInfo;

    List<BtnInfo> plList;
    List<BtnInfo> mealList;
    List<BtnInfo> postAct;
    List<BtnInfo> reqItem;
    int stateIdx = 0;

    private boolean side = false;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Intent intent = new Intent(this, ChatActivity.class);
        switch (item.getItemId()) {
            case R.id.pre:
                intent.putExtra("state", 0);
                startActivity(intent);
                return true;
            case R.id.in:

                intent.putExtra("state", 1);
                startActivity(intent);
                return true;
            case R.id.post:
                intent.putExtra("state", 2);
                startActivity(intent);
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        flightInfo = new FlightInfo();

        buttonSend = (Button) findViewById(R.id.buttonSend);

        listView = (ListView) findViewById(R.id.listView1);

        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.activity_chat_singlemessage);
        listView.setAdapter(chatArrayAdapter);

        chatText = (EditText) findViewById(R.id.chatText);
        chatText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage(chatText.getText().toString());
                }
                return false;
            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage(chatText.getText().toString());
            }
        });

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);


        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });



        flightInfo.setInfo();
        mealList = new ArrayList<>();
        mealList.add(new BtnInfo("meal", "Edit PlayList", 10));
        mealList.add(new BtnInfo("meal", "Choose Meal", 11));
        plList = new ArrayList<>();
        plList.add(new BtnInfo("pl", "m1", 20));
        plList.add(new BtnInfo("pl", "m2", 21));
        plList.add(new BtnInfo("pl", "m3", 22));
        postAct = new ArrayList<>();
        postAct.add(new BtnInfo("postact", "Feedback", 41));
        postAct.add(new BtnInfo("postact", "Check Luggage", 42));
        reqItem = new ArrayList<>();
        reqItem.add(new BtnInfo("item", "Blanket", 31));
        reqItem.add(new BtnInfo("item", "Drink", 32));
        reqItem.add(new BtnInfo("item", "Noodle", 32));
        reqItem.add(new BtnInfo("item", "Nuts", 32));


        Intent i= getIntent();
        stateIdx = i.getIntExtra("state", 0);

        if(stateIdx == 0) {
            chatArrayAdapter.add(new ChatMessage(true, 0, "Hello!"));
            chatArrayAdapter.add(new ChatMessage(true, 1, flightInfo));
            chatArrayAdapter.add(new ChatMessage(true, 0, "Choose Meal or Edit Playlist"));
        }else if(stateIdx == 1) {
            chatArrayAdapter.add(new ChatMessage(true, 0, "Welcome Aboard "));
            chatArrayAdapter.add(new ChatMessage(true, 0, "How can I help you"));
            chatArrayAdapter.add(new ChatMessage(true, 2, reqItem));
        }else if(stateIdx == 2) {
            chatArrayAdapter.add(new ChatMessage(true, 2, postAct));
        }



    }



    public boolean sendChatMessage(String msg){
        chatArrayAdapter.add(new ChatMessage(side, 0, msg));
        chatText.setText("");
        //side = !side;
        if(msg.equalsIgnoreCase("choose meal")) {
            chatArrayAdapter.add(new ChatMessage(true, 0, "We have Chicken and Beef, which one do you want?"));
        }else if (msg.equalsIgnoreCase("edit playlist")) {
            chatArrayAdapter.add(new ChatMessage(true, 0, "Here is the Movies"));
            chatArrayAdapter.add(new ChatMessage(true, 3, plList));
        }else {
            chatArrayAdapter.add(new ChatMessage(true, 0, "OK"));
        }

        return true;
    }



}