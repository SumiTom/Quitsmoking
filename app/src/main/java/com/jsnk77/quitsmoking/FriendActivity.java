package com.jsnk77.quitsmoking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;



/**
 * Created by yuki on 2014/12/10.
 */
public class FriendActivity extends Activity {

    @InjectView(R.id.friend_image)
    ImageView mFriendImage;
    @InjectView(R.id.friend_name)
    TextView mFriendName;
    @InjectView(R.id.m1_button)
    Button mM1Button;
    @InjectView(R.id.m2_button)
    Button mM2Button;
    @InjectView(R.id.m3_button)
    Button mM3Button;
    @InjectView(R.id.friend_total_count)
    TextView mFriendTotalCount;
    @InjectView(R.id.friend_day_count)
    TextView mFriendDayCount;

    Intent intent;

    private String fbId;
    private String fbName;
    private MobileServiceClient mClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        ButterKnife.inject(this);

        intent = getIntent();

        mFriendName.setText("YUJI NAKANISHI");
        mFriendTotalCount.setText("Total:20");
        mFriendDayCount.setText("");

       fbId = intent.getStringExtra("fbId");
       fbName = intent.getStringExtra("fbName");


    }

    //いいね
    @OnClick(R.id.m1_button)
    public void M1_Button() {
  /*      // TODO submit data to server...
        try {
            mClient = new MobileServiceClient(
                    "https://fb-messages.azure-mobile.net/",
                    "CQNkIViRgIWYSsFTsGcUMpcLedvvyB33",
                    this
            );
            MessageListItem message = new MessageListItem();
            message.id = "8C9F690D-6418-4838-A80B-23FF4EC29A57";
            message.FromFbId = "aaja88wq0liavao8sehhava";
            message.ToFbId = "ajoaehnvlia;ndocana";
            message.read= false;
            message.Message = "いいね！";
            mClient.getTable(MessageListItem.class).insert(message, new TableOperationCallback<MessageListItem>() {
                public void onCompleted(MessageListItem entity, Exception exception, ServiceFilterResponse response) {
                    if (exception == null) {
                        // Insert succeeded
                    } else {
                        // Insert failed
                    }
                }
            });

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        */
    }

    //がんばれ！
    @OnClick(R.id.m2_button)
    public void M2_Button() {
 /*       // TODO submit data to server...
        try {
            mClient = new MobileServiceClient(
                    "https://fb-messages.azure-mobile.net/",
                    "CQNkIViRgIWYSsFTsGcUMpcLedvvyB33",
                    this
            );
            MessageListItem message = new MessageListItem();
            message.id = "8C9F690D-6418-4838-A80B-23FF4EC29A57";
            message.FromFbId = "aaja88wq0liavao8sehhava";
            message.ToFbId = "ajoaehnvlia;ndocana";
            message.read= false;
            message.Message = "がんばれ！";
            mClient.getTable(MessageListItem.class).insert(message, new TableOperationCallback<MessageListItem>() {
                public void onCompleted(MessageListItem entity, Exception exception, ServiceFilterResponse response) {
                    if (exception == null) {
                        // Insert succeeded
                    } else {
                        // Insert failed
                    }
                }
            });

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        */
    }

    //ダメね。
    @OnClick(R.id.m3_button)
    public void M3_Button() {
  /*      // TODO submit data to server...
        try {
            mClient = new MobileServiceClient(
                    "https://fb-messages.azure-mobile.net/",
                    "CQNkIViRgIWYSsFTsGcUMpcLedvvyB33",
                    this
            );
            MessageListItem message = new MessageListItem();
            message.id = "8C9F690D-6418-4838-A80B-23FF4EC29A57";
            message.FromFbId = "aaja88wq0liavao8sehhava";
            message.ToFbId = "ajoaehnvlia;ndocana";
            message.read= false;
            message.Message = "ダメね。 ";
            mClient.getTable(MessageListItem.class).insert(message, new TableOperationCallback<MessageListItem>() {
                public void onCompleted(MessageListItem entity, Exception exception, ServiceFilterResponse response) {
                    if (exception == null) {
                        // Insert succeeded
                    } else {
                        // Insert failed
                    }
                }
            });

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

   */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


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
}
