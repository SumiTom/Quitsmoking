package com.jsnk77.quitsmoking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.LoggingBehavior;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableOperationCallback;
import com.microsoft.windowsazure.mobileservices.TableQueryCallback;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity {



//    Facebook
    private UiLifecycleHelper uiHelper;
    private static final String TAG = "MainActivity";
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };



//    Azure
    private MobileServiceClient mClient;
    private  User users = new User();

//    Intent処理
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginButton authButton = (LoginButton)findViewById(R.id.authButton);

        authButton.setReadPermissions(Arrays.asList("read_friendlists", "user_likes", "public_profile"));
        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);

        try {
            mClient = new MobileServiceClient( "https://quitsmokingavatar.azure-mobile.net/", "oBosiqBqMTabSBiLzeCmUlAucgoiOX80", this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



        Settings.addLoggingBehavior(LoggingBehavior.REQUESTS);


    }

        private void onSessionStateChange(Session session, SessionState state, Exception exception) {
            if (state.isOpened()) {


                Log.i(TAG, "Logged in...");
                // Request user data and show the results
                Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {

                    @Override
                    public void onCompleted(GraphUser user, Response response) {
                        if(user != null) {
                         buildUserInfoDisplay(user);
                         }
                   }
                });
            }
            else if (state.isClosed()) {
                        Log.i(TAG, "Logged out...");

                         }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        Session session = Session.getActiveSession();
        if (session != null &&
                (session.isOpened() || session.isClosed()) ) {
            onSessionStateChange(session, session.getState(), null);
        }

        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    private  void buildUserInfoDisplay(GraphUser user) {
        StringBuilder userInfo = new StringBuilder("");

        // Example: typed access (name)
        // - no special permissions required
        userInfo.append(String.format("Name: %s\n\n",
                user.getName()));

        // Example: typed access (birthday)
        // - requires user_birthday permission
        userInfo.append(String.format("Birthday: %s\n\n",
                user.getBirthday()));

        // Example: partially typed access, to location field,
        // name key (location)
        // - requires user_location permission
        userInfo.append(String.format("Location: %s\n\n",
                user.getLocation().getProperty("name")));

        // Example: access via property name (locale)
        // - no special permissions required
        userInfo.append(String.format("Locale: %s\n\n",
                user.getProperty("locale")));




        users.name=user.getName();
        users.age=user.getBirthday();
        users.gender= (String) user.getProperty(String.format("gender"));
        users.fbId=user.getId();


        checkId(user);
        CheckFriendsId(user);

        intent = new Intent(this,HomeActivity.class);
        intent.putExtra("fbId", user.getId());
        intent.putExtra("fbName",user.getName());
        intent.putExtra("fbGender", (String) user.getProperty("gender"));
        intent.putExtra("fbBirthday",user.getBirthday());
        startActivity(intent);
        finish();


    }



    public void checkId(final GraphUser user){
        MobileServiceTable<User> id  = mClient.getTable(User.class);

       id.where().field("fbId").eq(user.getId()).execute(new TableQueryCallback<User>() {
           @Override
           public void onCompleted(List<User> result, int count, Exception exception, ServiceFilterResponse response) {

               if(result.size() >  0 ){
                   //Id has already registered.
                   Toast.makeText(MainActivity.this, "こんにちは"+user.getName()+"さん禁煙は捗っていますか？", Toast.LENGTH_SHORT).show();


               }else if(result.size() == 0){
                   //Id needs to be inserted.

                   mClient.getTable(User.class).insert(users, new TableOperationCallback<User>() {
                       public void onCompleted(User entity, Exception exception, ServiceFilterResponse response) {
                           if (exception == null) {
                               // Insert succeeded
                               Toast.makeText(MainActivity.this,"アカウントの新規作成が完了しました¥nこれから禁煙頑張りましょう！",Toast.LENGTH_SHORT ).show();
                           }
                           else {
                               // Insert failed
                           }
                       }
                   });

               }
           }
       });

    }

    private void CheckFriendsId(GraphUser user) {

    }
}
