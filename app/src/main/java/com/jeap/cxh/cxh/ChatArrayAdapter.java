package com.jeap.cxh.cxh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeap.cxh.cxh.model.BtnInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {
    private Context context;
	private TextView chatText;
    private LinearLayout card, btnLL;
	private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
	private LinearLayout singleMessageContainer;


	@Override
	public void add(ChatMessage object) {
		chatMessageList.add(object);
		super.add(object);
	}

	public ChatArrayAdapter( Context context, int textViewResourceId) {
		super(context, textViewResourceId);
        this.context = context;

    }

	public int getCount() {
		return this.chatMessageList.size();
	}

	public ChatMessage getItem(int index) {
		return this.chatMessageList.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.activity_chat_singlemessage, parent, false);
		}
        ChatMessage chatMessageObj = getItem(position);
        singleMessageContainer = (LinearLayout) row.findViewById(R.id.singleMessageContainer);

        chatText = (TextView) row.findViewById(R.id.singleMessage);
        chatText.setText(chatMessageObj.message);
        card = (LinearLayout) row.findViewById(R.id.cardTV);
        btnLL = (LinearLayout) row.findViewById(R.id.listBTN);


        float density = this.getContext().getResources().getDisplayMetrics().density;
        int paddingDp = (int) (25 * density);
        int otherDp = (int) (15 * density);
        if (chatMessageObj.left)
            chatText.setPadding(paddingDp, otherDp, otherDp, otherDp);
        else
            chatText.setPadding(otherDp, otherDp, paddingDp, otherDp);


        chatText.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;

        chatText.setBackgroundResource(chatMessageObj.left ? R.drawable.balloon_incoming_normal : R.drawable.balloon_outgoing_normal);
        singleMessageContainer.setGravity(chatMessageObj.left ? Gravity.LEFT : Gravity.RIGHT);

        if (chatMessageObj.msgType == 0) {
            chatText.setVisibility(View.VISIBLE);
            btnLL.setVisibility(View.GONE);
            card.setVisibility(View.GONE);

        } else if (chatMessageObj.msgType == 1) {

            TextView tv1 = (TextView) card.findViewById(R.id.depaTV);
            TextView tv2 = (TextView) card.findViewById(R.id.destTV);
            TextView tv3 = (TextView) card.findViewById(R.id.gateTV);
            TextView tv4 = (TextView) card.findViewById(R.id.timeTV);
            TextView tv5 = (TextView) card.findViewById(R.id.seatTV);
            tv1.setText(chatMessageObj.flightInfo.departure);
            tv2.setText(chatMessageObj.flightInfo.arrival);
            tv3.setText(chatMessageObj.flightInfo.gate);
            tv4.setText(chatMessageObj.flightInfo.time);
            tv5.setText(chatMessageObj.flightInfo.seat);

            chatText.setVisibility(View.GONE);
            btnLL.setVisibility(View.GONE);
            card.setVisibility(View.VISIBLE);

        } else if (chatMessageObj.msgType == 2) {

            if((btnLL).getChildCount() > 0)
                ( btnLL).removeAllViews();

            int iNumberOfButtons =  chatMessageObj.listObj.size();
            Button[] dynamicButtons = new Button[iNumberOfButtons];

            LinearLayout.LayoutParams paramsButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            for (int i = 0; i < iNumberOfButtons; i++) {
                BtnInfo btnItem = (BtnInfo) chatMessageObj.listObj.get(i);
                ContextThemeWrapper newContext = new ContextThemeWrapper(this.getContext(), R.style.MyButton);
                dynamicButtons[i] = new Button(newContext);
                dynamicButtons[i].setText(btnItem.getName());
                dynamicButtons[i].setId(btnItem.getId());
                dynamicButtons[i].setTextSize(15.0f);
                dynamicButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                dynamicButtons[i].setLayoutParams(paramsButton);
                btnLL.addView(dynamicButtons[i]);
            }


            chatText.setVisibility(View.GONE);
            btnLL.setVisibility(View.VISIBLE);
            card.setVisibility(View.GONE);

        }else if (chatMessageObj.msgType == 3) {

            if((btnLL).getChildCount() > 0)
                ( btnLL).removeAllViews();

            int iNumberOfButtons =  chatMessageObj.listObj.size();
            Button[] dynamicButtons = new Button[iNumberOfButtons];

            LinearLayout.LayoutParams paramsButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int[] myImageList = new int[]{R.drawable.m1, R.drawable.m2, R.drawable.m3};
            for (int i = 0; i < iNumberOfButtons; i++) {
                ImageView myImageView = new ImageView(context);
                //myImageView.setImageResource();
                final float scale = 3;
                int dpWidthInPx  = (int) (130 * scale);
                int dpHeightInPx = (int) (180 * scale);
                Picasso.with(context).load(myImageList[i]).resize(dpWidthInPx, dpHeightInPx).into(myImageView);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dpWidthInPx, dpHeightInPx);
                layoutParams.setMargins(5, 0, 5, 0);
                myImageView.setLayoutParams(layoutParams);
                myImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                btnLL.addView(myImageView);
            }


            chatText.setVisibility(View.GONE);
            btnLL.setVisibility(View.VISIBLE);
            card.setVisibility(View.GONE);

        }
		return row;
	}




}