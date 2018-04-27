package com.hotupdate.receiver

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.hotupdate.*
import com.hotupdate.extension.logD
import com.hotupdate.extension.toast
import com.hotupdate.manager.DownloadTask
import com.hotupdate.utils.BsdiffUtils
import com.hotupdate.utils.FileUtils


/**
 * Created by ImL1s on 2018/4/19.
 * Description:
 */
class CompleteReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val completeId = intent!!.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -2)
        if (completeId == DownloadTask.INSTANCE.currentDownloadID) {
            val firstZipName = FileUtils.decompression()
            DownloadTask.INSTANCE.currentDownloadPatchName = firstZipName
//            DownloadTask.INSTANCE.zipFile.delete()
            mergePathAndAsset(context!!)
        }
    }

    private fun mergePathAndAsset(context: Context) {
        if (DownloadTask.INSTANCE.currentDownloadPatchName == null) {
            context.getString(R.string.network_unavailable)
                    .toast(context)
                    .logD(Tag.DEBUG.value)
        }
        // 首次開啟app,將asset目錄下的index.android.bundle複製到sd卡下
        else if (MainApplication.getInstance().isFirstUpdate) {
            // region
//            val commonAssetBundleString = FileUtils.getJsBundleStringFromAssets(MainApplication.getInstance())
//            val patchString = FileUtils.getStringFromPatch(JS_PATCH_LOCAL_FILE(DownloadTask.INSTANCE.currentDownloadPatchName!!))
//            if (!patchString.isNullOrEmpty()) {
//                BsdiffUtils.patch(commonAssetBundleString, patchString!!, JS_BUNDLE_LOCAL_PATH)
//            } else {
//                Log.d("debug", "patchString is null or empty")
//            }
            // endregion
            val paddingResult = goAsync()
            Thread {
                FileUtils.copyAssetToPath(context, JS_BUNDLE_LOCAL_FILE_NAME, JS_BUNDLE_TEMP_LOCAL_PATH)
                BsdiffUtils.patch(JS_BUNDLE_TEMP_LOCAL_PATH, JS_BUNDLE_LOCAL_PATH, JS_PATCH_LOCAL_FILE(DownloadTask.INSTANCE.currentDownloadPatchName!!))
                // TODO 將config.json(本次更新訊息)儲存起來
                Log.d(Tag.DEBUG.value, "更新成功!!")
                val intent = Intent()
                intent.action = ACTION_NATIVE_RECEIVER
                intent.putExtra(BroadcastSignal.SIGNAL.name, BroadcastSignal.TOAST.name)
                intent.putExtra(BroadcastSignal.TOAST_CONTENT.name, "更新成功!!")
                context.sendBroadcast(intent)
                paddingResult.finish()
            }.start()

        } else { // 之前已經將index.android.bundle複製到sd卡下方,只需要進行bundle patch
            // TODO
            Log.d(Tag.DEBUG.value, "TODO!!")
        }
    }
}