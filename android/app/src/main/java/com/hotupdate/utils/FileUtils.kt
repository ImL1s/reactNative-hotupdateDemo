package com.hotupdate.utils

import android.content.Context
import com.hotupdate.JS_BUNDLE_LOCAL_FILE_NAME
import com.hotupdate.JS_PATCH_LOCAL_FOLDER
import com.hotupdate.manager.DownloadTask
import java.io.*
import java.nio.charset.Charset
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream


/**
 * Created by ImL1s on 2018/4/19.
 * Description:
 */
object FileUtils {

    fun decompression() {
        val zipInputStream = ZipInputStream(FileInputStream(DownloadTask.INSTANCE.zipFile))
        var zipEntry: ZipEntry?
        var szName: String
        zipEntry = zipInputStream.nextEntry

        while (zipEntry != null) {
            szName = zipEntry.name
            if (zipEntry.isDirectory) {
                szName = szName.substring(0, szName.length - 1)
                val folder = File(JS_PATCH_LOCAL_FOLDER + File.separator + szName)
                folder.mkdirs()
            } else {
                val file = File(JS_PATCH_LOCAL_FOLDER + File.separator + szName)
                val createSucc = file.createNewFile()
                val fileOutputStream = FileOutputStream(file)
                var len = 0
                val buffer = ByteArray(1024)

                while ({ len = zipInputStream.read(buffer); len }() != -1) {
                    fileOutputStream.write(buffer, 0, len)
                    fileOutputStream.flush()
                }
                fileOutputStream.close()
            }
            zipEntry = zipInputStream.nextEntry
        }

        zipInputStream.close()
    }

    fun getJsBundleStringFromAssets(context: Context): String {
        val inputStream: InputStream = context.assets.open(JS_BUNDLE_LOCAL_FILE_NAME)
        val size = inputStream.available()
        val memoryStream = ByteArrayOutputStream(size)
        return try {
            val buffer = ByteArray(size)
            var len = 0
            while ({ len = inputStream.read(buffer); len }() != -1) {
                memoryStream.write(buffer, 0, len)
            }
            val result = memoryStream.toByteArray()
            inputStream.close()
            memoryStream.close()
            String(result, Charset.forName("UTF-8"))

        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}