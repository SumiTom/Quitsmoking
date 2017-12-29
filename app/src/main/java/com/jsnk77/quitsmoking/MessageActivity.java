package com.jsnk77.quitsmoking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yuki on 2014/12/13.
 */


public class MessageActivity extends ActionBarActivity {
    @InjectView(R.id.textView)
    TextView mTextView;
    @InjectView(R.id.listView)
    ListView mListView;
    ArrayList<MessageListItem> users;
    Intent intent;
    private String fbId;
    private String fbName;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.inject(this);


        intent = getIntent();
        fbId = intent.getStringExtra("fbId");
        fbName = intent.getStringExtra("fbName");

        //ListView setup
        users = new ArrayList<MessageListItem>();
        String s = " さんからメッセージデス";

        MessageListItem u1 = new MessageListItem();
        u1.fromfriendname = ("とーま"+ s );
        u1.Message = "いいね！";
        users.add(u1);

        MessageListItem u2 = new MessageListItem();
        u2.fromfriendname = ("じゅんき" + s );
        u2.Message = "がんばれ！";
        users.add(u2);

        MessageListItem u3 = new MessageListItem();
        u3.fromfriendname = ("とーま"+ s );
        u3.Message = "いいね！";
        users.add(u3);

        MessageListItem u4 = new MessageListItem();
        u4.fromfriendname = ("じゅんき" + s );
        u4.Message = "がんばれ！";
        users.add(u4);

        MessageListItem u5 = new MessageListItem();
        u5.fromfriendname = ("とーま"+ s );
        u5.Message = "いいね！";
        users.add(u5);

        MessageListItem u6 = new MessageListItem();
        u6.fromfriendname = ("じゅんき" + s );
        u6.Message = "がんばれ！";
        users.add(u6);

        //set ListAdapter
        MessageListAdapter messageListAdapter = new MessageListAdapter(this, R.layout.activity_messagelistitem, users);
        mListView.setAdapter(messageListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = new Intent();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        } else*/
        if (id == R.id.action_home) {
            //Toast.makeText(this, "Main Page selected", Toast.LENGTH_LONG).show();
            intent.setClassName("com.jsnk77.quitsmoking", "com.jsnk77.quitsmoking.HomeActivity");
            //intent.putExtra("Goal", "");
            intent.putExtra("fbId",fbId);
            intent.putExtra("fbName",fbName);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.action_profile) {
            //Toast.makeText(this, "profile selected", Toast.LENGTH_LONG).show();
            intent.setClassName("com.jsnk77.quitsmoking", "com.jsnk77.quitsmoking.ProfileActivity");
            intent.putExtra("fbId",fbId);
            intent.putExtra("fbName",fbName);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.action_message) {
            //Toast.makeText(this, "facebook selected", Toast.LENGTH_LONG).show();
            intent.setClassName("com.jsnk77.quitsmoking", "com.jsnk77.quitsmoking.MessageActivity");
            intent.putExtra("fbId",fbId);
            intent.putExtra("fbName",fbName);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class MessageListAdapter extends ArrayAdapter<MessageListItem> {

        Context mContext;
        int mResource;

        public MessageListAdapter(Context context, int resource, List<MessageListItem> objects) {
            super(context, resource, objects);
            this.mContext = context;
            this.mResource = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }

            MessageListItem item = this.getItem(position);
            holder.mFriendName.setText(item.fromfriendname);
            holder.mMessage.setText(item.Message);

            // etc...

            return convertView;
        }


        static class ViewHolder {
            @InjectView(R.id.friendname)
            TextView mFriendName;
            @InjectView(R.id.message)
            TextView mMessage;

            ViewHolder(View view) {
                ButterKnife.inject(this, view);
            }
        }
    }
}


