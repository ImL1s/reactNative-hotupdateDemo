package com.hotupdate.activity

import android.Manifest
import android.app.Activity
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.TextView
import com.hotupdate.JS_PATCH_LOCAL_FOLDER
import com.hotupdate.PathConstant
import com.hotupdate.receiver.CompleteReceiver
import com.hotupdate.utils.BsdiffUtils
import com.hotupdate.utils.UpdateUtils
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter


/**
 * Created by ImL1s on 2018/4/19.
 * Description:
 */
class PreLoadActivity : Activity() {

    private var sdCard = Environment.getExternalStorageDirectory()
    private var file1 = File(sdCard.absolutePath + "/test1.txt")
    private var file2 = File(sdCard.absolutePath + "/test2.txt")
    private var file3 = File(sdCard.absolutePath + "/test3.patch")
    private var file4 = File(sdCard.absolutePath + "/test4.txt")
    private var receiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(TextView(this))
        PathConstant.ensurePath()
        requestPermission()
        registerReceiver()
        UpdateUtils.checkFirstUpdate(this, JS_PATCH_LOCAL_FOLDER)
        UpdateUtils.checkVersion(this)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    override fun onDestroy() {
        super.onDestroy()
        unRegisterCustomReceiver()
    }

    private fun registerReceiver() {
        if (receiver == null) receiver = CompleteReceiver()
        registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    private fun unRegisterCustomReceiver() {
        if (receiver != null) unregisterReceiver(receiver)
    }

    private fun requestPermission() {
//        if (ContextCompat.checkSelfPermission(this,
//                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
//        }
        ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CALL_PHONE), 1)
    }

    /**
     * 生成兩個測試文件來做diff測試
     */
    // TODO move to test
    protected fun writeTestFile() {
        try {
            val outputStream1 = FileOutputStream(file1)
            val outputStream2 = FileOutputStream(file2)
            val p1 = PrintWriter(outputStream1)
            val p2 = PrintWriter(outputStream2)
            p1.println("a")
            p1.println("b")
            p1.println("c")

            p2.println("a")
            p2.println("c")
            p2.println("d")
            p1.close()
            p2.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    // TODO move to test
    protected fun jniDiffAndPatchTest() {
        val diff = BsdiffUtils.diff(file1.absolutePath, file2.absolutePath, file3.absolutePath)
        val patch = BsdiffUtils.patch(file1.absolutePath, file4.absolutePath, file3.absolutePath)
        Log.d("hotupdate", "diff: $diff")
        Log.d("hotupdate", "patch: $patch")
    }



}

