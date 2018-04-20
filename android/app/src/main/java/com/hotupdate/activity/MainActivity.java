package com.hotupdate.activity;

import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.hotupdate.PathConstantKt;
import com.hotupdate.utils.UpdateUtils;

import java.io.File;

public class MainActivity extends ReactActivity {


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
//        writeTestFile();
//        jniDiffAndPatchTest();
//        new File(PathConstantKt.getJS_PATCH_LOCAL_FOLDER()).mkdirs();
    }
}
