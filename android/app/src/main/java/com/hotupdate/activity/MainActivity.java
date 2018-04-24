package com.hotupdate.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.react.ReactActivity;
import com.hotupdate.BroadcastSignal;

import static com.hotupdate.ConstantKt.ACTION_NATIVE_RECEIVER;

public class MainActivity extends ReactActivity {

    BroadcastReceiver receiver;

    /**
     * Returns the name of the main componentp registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "hotUpdate";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (receiver == null) receiver = new NativeReceiver();
        registerReceiver(receiver, new IntentFilter(ACTION_NATIVE_RECEIVER));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) unregisterReceiver(receiver);
    }

    class NativeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            String signalType = intent.getStringExtra(BroadcastSignal.SIGNAL.name());
            BroadcastSignal signalTypeEnum = BroadcastSignal.valueOf(signalType);
            switch (signalTypeEnum) {
                case TOAST:
                    final String toastContent = intent.getStringExtra(BroadcastSignal.TOAST_CONTENT.name());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, toastContent, Toast.LENGTH_LONG).show();
                        }
                    });
                    break;
            }
        }
    }
}
