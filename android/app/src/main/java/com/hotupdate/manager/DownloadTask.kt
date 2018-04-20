package com.hotupdate.manager

import com.hotupdate.JS_PATCH_LOCAL_PATH
import java.io.File


/**
 * Created by ImL1s on 2018/4/19.
 * Description:
 */
class DownloadTask {

    companion object {
        val INSTANCE: DownloadTask by lazy { DownloadTask() }
    }

    var currentDownloadID = -1L
    var zipFile = File(JS_PATCH_LOCAL_PATH)

}