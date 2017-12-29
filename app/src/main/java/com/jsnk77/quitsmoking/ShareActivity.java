package com.jsnk77.quitsmoking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;

/**
 * Created by jsnk77 on 14/12/13.
 */
public class ShareActivity extends Activity {

    private UiLifecycleHelper uiHelper;
    private String Name="禁煙アバター";
    private String Caption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

//      ◯◯から禁煙をはじめて、合計◯本吸ってて今日は◯本でした！ みんなも一緒に禁煙しよう！ 今日の本数とアバターをFacebookに投稿します


        uiHelper = new UiLifecycleHelper(this, null);
        uiHelper.onCreate(savedInstanceState);

        putCaption();

        FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(this)
                .setLink("https://developers.facebook.com/android").setName(Name).setCaption(Caption) .build();
        uiHelper.trackPendingDialogCall(shareDialog.present());
        finish();
    }

    private void putCaption() {
        Caption = ""+"から禁煙をはじめました！今日は"+""+"本吸いました";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
            @Override
            public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
                Log.e("Activity", String.format("Error: %s", error.toString()));
            }

            @Override
            public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
                Log.i("Activity", "Success!");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
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

}
