package com.hotupdate

import android.os.Environment
import java.io.File


/**
 * Created by ImL1s on 2018/4/19.
 * Description:
 */
const val JS_BUNDLE_LOCAL_FILE_NAME = "index.android.bundle"
const val JS_BUNDLE_TEMP_LOCAL_FILE_NAME = "index.android.temp.bundle"
const val JS_BUNDLE_FOLDER_NAME = "bundle"
const val MAIN_BUNDLE_FOLDER_NAME = "bundle"
const val DOWNLOAD_ZIP_NAME = "patch.zip"
const val JS_BUNDLE_REMOTE_URL = "http://127.0.0.1:5055/patch/1.0.0-1.0.1.patch.zip" // 伺服器熱更新patch下載地址

val LOCAL_FOLDER_NAME: String = MainApplication.getInstance().packageName
val JS_PATCH_LOCAL_FOLDER = Environment.getExternalStorageDirectory().toString() + File.separator + LOCAL_FOLDER_NAME

val LOCAL_FOLDER = "$JS_PATCH_LOCAL_FOLDER${File.separator}$MAIN_BUNDLE_FOLDER_NAME"


// 合併後的bundle路徑 ex:emulate/0/bundle/index.android.bundle
val JS_BUNDLE_LOCAL_PATH = JS_PATCH_LOCAL_FOLDER + "${File.separator}$JS_BUNDLE_FOLDER_NAME${File.separator}" + JS_BUNDLE_LOCAL_FILE_NAME

// 從asset取出後的bundle暫存路徑 ex:emulate/0/bundle/index.android.temp.bundle
val JS_BUNDLE_TEMP_LOCAL_PATH = JS_PATCH_LOCAL_FOLDER + "${File.separator}$JS_BUNDLE_FOLDER_NAME${File.separator}" + JS_BUNDLE_TEMP_LOCAL_FILE_NAME

val JS_BUNDLE_LOCAL_FOLDER = "$JS_PATCH_LOCAL_FOLDER${File.separator}$JS_BUNDLE_FOLDER_NAME"

// 下載後的zip文件
val JS_PATCH_LOCAL_PATH = "$JS_PATCH_LOCAL_FOLDER${File.separator}$JS_BUNDLE_FOLDER_NAME${File.separator}$DOWNLOAD_ZIP_NAME"

// 解壓縮後的patch文件
//val JS_PATCH_LOCAL_FILE = JS_PATCH_LOCAL_FOLDER + "${File.separator}$JS_BUNDLE_FOLDER_NAME${File.separator}" + "bundle.patch"
fun JS_PATCH_LOCAL_FILE(fileName: String): String {
    return JS_PATCH_LOCAL_FOLDER + "${File.separator}$JS_BUNDLE_FOLDER_NAME${File.separator}$fileName"
}

fun getSdCardPath(path: String): String {
    val sdCard = Environment.getExternalStorageDirectory()
    return "${sdCard.absoluteFile}$JS_PATCH_LOCAL_PATH"
}

object PathConstant {
    fun ensurePath() {
        val bundleDownloadPath = File(JS_PATCH_LOCAL_PATH)
        if (!bundleDownloadPath.exists()) {
            bundleDownloadPath.mkdirs()
        }
    }
}
