package com.hotupdate.receiver

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hotupdate.JS_PATCH_LOCAL_PATH
import com.hotupdate.MainApplication
import com.hotupdate.activity.MainActivity
import com.hotupdate.manager.DownloadTask
import com.hotupdate.utils.FileUtils


/**
 * Created by ImL1s on 2018/4/19.
 * Description:
 */
class CompleteReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val completeId = intent!!.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
        if (completeId == DownloadTask.INSTANCE.currentDownloadID) {
            FileUtils.decompression()
            DownloadTask.INSTANCE.zipFile.delete()
            mergePathAndAsset()
            context?.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    private fun mergePathAndAsset() {
        val commonAssetBundleString = FileUtils.getJsBundleStringFromAssets(MainApplication.getInstance())
//        val patchString = FileUtils.getStringFromPatch(JS_PATCH_LOCAL_PATH)
    }
}