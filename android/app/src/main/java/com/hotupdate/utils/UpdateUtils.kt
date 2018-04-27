package com.hotupdate.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.util.Log
import com.hotupdate.*
import com.hotupdate.extension.logD
import com.hotupdate.extension.toastLong
import com.hotupdate.manager.DownloadTask
import com.hotupdate.retrofit.RetrofitFactory
import com.hotupdate.retrofit.UpdateService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File


/**
 * Created by ImL1s on 2018/4/19.
 * Description:
 */
object UpdateUtils {

    @JvmStatic
    fun checkVersion(context: Context) {
        val updateService = RetrofitFactory.getService(UpdateService::class.java)
        updateService.checkLastVersion("0.1.0", "1.0.0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ lastVersionResult ->
                    if (lastVersionResult.needUpdate!! && lastVersionResult.canUpdate!!) {
                        val message = context.getString(R.string.begin_download)
                        message.toastLong(context)
                        message.logD(Tag.DEBUG.value)

                        downloadBundle(context, lastVersionResult.patchURL!!)
                    }
                }, { error ->
                    Log.e(Tag.ERROR.value, error.toString())
                })
    }

    @JvmStatic
    fun checkFirstUpdate(context: Context, filePath: String) {
        val bundleFile = File(filePath)
//        MainApplication.getInstance().isFirstUpdate = !bundleFile.exists()
        MainApplication.getInstance().isFirstUpdate = true
    }

    private fun downloadBundle(context: Context) {
        downloadBundle(context, JS_BUNDLE_REMOTE_URL)
    }

    private fun downloadBundle(context: Context, url: String) {
        val zipFile = DownloadTask.INSTANCE.zipFile
        if (zipFile.exists()) {
            zipFile.delete()
        }
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(url))
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                .setDestinationInExternalPublicDir(LOCAL_FOLDER_NAME,
                        MAIN_BUNDLE_FOLDER_NAME + File.separator + DOWNLOAD_ZIP_NAME)
//                .setDestinationUri(Uri.parse("file://$JS_PATCH_LOCAL_FOLDER"))
        DownloadTask.INSTANCE.currentDownloadID = downloadManager.enqueue(request)
    }

}