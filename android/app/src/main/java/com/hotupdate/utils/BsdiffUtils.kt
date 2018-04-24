package com.hotupdate.utils


/**
 * Created by ImL1s on 2018/4/18.
 * Description:
 */
public class BsdiffUtils {
    companion object {
        init {
            System.loadLibrary("bsdiff")
        }

        /**
         * 進行diff拆分,比對oldFilePath與newFile的差異,並生成以oldFile為基準的diff補丁.
         *
         * @param oldFilePath 基準檔案路徑.
         * @param newFilePath 差異檔案路徑.
         * @param patchPath  生成的diff檔案要放置的路徑.
         */
        @JvmStatic
        external fun diff(oldFilePath: String, newFilePath: String, patchPath: String): Int


        /**
         * 對基準檔案進行打補丁.
         *
         * @param oldPath 要打上diff補丁的基準檔案.
         * @param newPath 基準檔案+diff補丁生成的新檔案放置的路徑.
         * @param pathPath diff補丁的路徑.
         */
        @JvmStatic
        external fun patch(oldPath: String, newPath: String, pathPath: String): Int
    }


}