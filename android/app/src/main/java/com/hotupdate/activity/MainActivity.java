package com.hotupdate.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.hotupdate.BroadcastSignal;
import com.hotupdate.R;
import com.hotupdate.extension.ToastExtensionKt;

import static com.hotupdate.ConstantKt.ACTION_NATIVE_RECEIVER;

public class MainActivity extends ReactActivity {

    BroadcastReceiver receiver;

    /**
     * Returns the name of the main component registered from JavaScript.
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
                    final String toastContent = intent.
                            getStringExtra(BroadcastSignal.TOAST_CONTENT.name());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastExtensionKt.toastLong(toastContent, context);
                            showUpdateSuccDialog(context);
                        }
                    });
                    break;
            }
        }

        private void showUpdateSuccDialog(Context context) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder =
                        new AlertDialog.Builder(context,
                                android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(context);
            }
            builder.setTitle(R.string.hot_update_succ)
                    .setMessage(R.string.is_reload_now)
                    .setPositiveButton(android.R.string.yes,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    restartActivity();
                                }
                            })
                    .setNegativeButton(android.R.string.no,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        private void restartActivity() {
            finish();
            startActivity(getIntent());
        }
    }
}
