package com.hotupdate;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.facebook.react.ReactActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class MainActivity extends ReactActivity {

    File sdCard = Environment.getExternalStorageDirectory();
    File file1 = new File(sdCard.getAbsolutePath() + "/test1.txt");
    File file2 = new File(sdCard.getAbsolutePath() + "/test2.txt");
    File file3 = new File(sdCard.getAbsolutePath() + "/test3.patch");

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
        writeTestFile();
        int diff = BsdiffUtils.diff(file1.getAbsolutePath(), file2.getAbsolutePath(), file3.getAbsolutePath());
        Log.d("wwwww","diff: " + diff);


    }

    /**
     * 生成兩個測試文件來做diff測試
     */
    protected void writeTestFile(){
        try {
            FileOutputStream outputStream1 = new FileOutputStream(file1);
            FileOutputStream outputStream2 = new FileOutputStream(file2);
            PrintWriter p1 = new PrintWriter(outputStream1);
            PrintWriter p2 = new PrintWriter(outputStream2);
            p1.println("a");
            p1.println("b");
            p1.println("c");

            p2.println("a");
            p2.println("c");
            p2.println("d");
            p1.close();
            p2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
