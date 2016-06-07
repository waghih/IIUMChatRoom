package com.waghih.iiumchatroom;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.client.Query;

/**
 * Created by farooq on 27/2/2016.
 */
public class ChatListAdapter  extends FirebaseListAdapter<Chat> {

    private String mUsername;

    public ChatListAdapter(Query ref, Activity activity, int layout, String mUsername) {
        super(ref, Chat.class, layout, activity);
        this.mUsername = mUsername;
    }

    @Override
    protected void populateView(View view, Chat chat) {
        String author = chat.getAuthor();
        TextView authorText = (TextView) view.findViewById(R.id.author);
        TextView msg = (TextView) view.findViewById(R.id.message);
        authorText.setText(author);

        LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams)msg.getLayoutParams();
        LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams)authorText.getLayoutParams();

        // If the message was sent by this user, color it differently
        if (author != null && author.equals(mUsername)) {
//            authorText.setTextColor(Color.RED);
            msg.setBackgroundResource(R.drawable.bubble_right_green);
            msg.setPadding(20,15,45,15);
            lp1.gravity = Gravity.RIGHT;
            lp1.setMargins(90,0,25,0);
            lp2.gravity = Gravity.RIGHT;
            lp2.setMargins(90,0,25,0);
        } else {
//            authorText.setTextColor(Color.BLUE);
            msg.setBackgroundResource(R.drawable.bubble_left_gray);
            msg.setPadding(45,15,20,15);
            lp1.gravity = Gravity.LEFT;
            lp1.setMargins(25,0,90,18);
            lp2.gravity = Gravity.LEFT;
            lp2.setMargins(25,0,90,0);
        }
//        ((TextView) view.findViewById(R.id.message)).setText(chat.getMessage());
        msg.setText(chat.getMessage());
    }

}
