package com.hotupdate


/**
 * Created by ImL1s on 2018/4/18.
 * Description:
 */
public class BsdiffUtils {
    companion object {
        init {
            System.loadLibrary("bsdiff")
        }

        @JvmStatic
        external fun diff(oldApkPath: String, newApkPath: String, patchPath: String): Int
    }



}