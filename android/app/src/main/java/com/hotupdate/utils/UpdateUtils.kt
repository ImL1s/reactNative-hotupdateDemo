package com.hotupdate.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.hotupdate.*
import com.hotupdate.manager.DownloadTask
import java.io.File


/**
 * Created by ImL1s on 2018/4/19.
 * Description:
 */
object UpdateUtils {

    @JvmStatic
    fun checkVersion(context: Context) {
        if (true) {
            Toast.makeText(context, "開始下載", Toast.LENGTH_LONG).show()
            downloadBundle(context)
        }
    }

    @JvmStatic
    fun checkFirstUpdate(context: Context, filePath: String) {
        val bundleFile = File(filePath)
//        MainApplication.getInstance().isFirstUpdate = !bundleFile.exists()
        MainApplication.getInstance().isFirstUpdate = true
    }

    private fun downloadBundle(context: Context) {
        val zipFile = DownloadTask.INSTANCE.zipFile
        if (zipFile.exists()) {
            zipFile.delete()
        }
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(JS_BUNDLE_REMOTE_URL))
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                .setDestinationInExternalPublicDir(LOCAL_FOLDER_NAME,
                        MAIN_BUNDLE_FOLDER_NAME + File.separator + DOWNLOAD_ZIP_NAME)
//                .setDestinationUri(Uri.parse("file://$JS_PATCH_LOCAL_FOLDER"))
        DownloadTask.INSTANCE.currentDownloadID = downloadManager.enqueue(request)
    }

}