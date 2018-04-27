package com.hotupdate;

import android.app.Application;
import android.util.Log;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

public class MainApplication extends Application implements ReactApplication {

    private static final String TAG = "hotupdate";

    private static MainApplication mInstance = null;

    public static MainApplication getInstance() {
        if (mInstance == null) {
            Log.d(TAG, "application instance not ok");
            return null;
        }
        return mInstance;
    }

    public boolean isFirstUpdate = false;


    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage()
            );
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }

        @Nullable
        @Override
        protected String getJSBundleFile() {
            File file = new File(PathConstantKt.getJS_BUNDLE_LOCAL_PATH());
            if (file.exists()) {
                return PathConstantKt.getJS_BUNDLE_LOCAL_PATH();
            }
            return super.getJSBundleFile();
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
        mInstance = this;
    }
}
