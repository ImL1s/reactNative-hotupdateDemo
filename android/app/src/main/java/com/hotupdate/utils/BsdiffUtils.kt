package com.hotupdate.utils


/**
 * Created by ImL1s on 2018/4/18.
 * Description:
 */
public class BsdiffUtils {
    companion object {
        init {
            System.loadLibrary("")
        }

        @JvmStatic
        external fun diff(oldApkPath: String, newApkPath: String, patchPath: String): Int

        @JvmStatic
        external fun patch(oldPath: String, newPath: String, pathPath: String): Int
    }


}