package com.hotupdate.utils

import android.content.Context
import com.hotupdate.JS_BUNDLE_LOCAL_FILE_NAME
import com.hotupdate.JS_BUNDLE_LOCAL_FOLDER
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

    fun decompression(): String? {
        val zipInputStream = ZipInputStream(FileInputStream(DownloadTask.INSTANCE.zipFile))
        var zipEntry: ZipEntry?
        var szName: String
        var firstZipName: String? = null
        var hasFirstZipName = false
        zipEntry = zipInputStream.nextEntry

        while (zipEntry != null) {
            szName = zipEntry.name
            if (zipEntry.isDirectory) {
                szName = szName.substring(0, szName.length - 1)
                val folder = File(JS_BUNDLE_LOCAL_FOLDER + File.separator + szName)
                folder.mkdirs()
            } else {
                if (!hasFirstZipName) {
                    firstZipName = szName
                    hasFirstZipName = true
                }
                val file = File(JS_BUNDLE_LOCAL_FOLDER + File.separator + szName)
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
        return firstZipName
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

    fun getStringFromPatch(patchPath: String): String? {
        val fileReader: FileReader
        var result: String? = null
        val stringBuilder: StringBuilder

        try {
            fileReader = FileReader(patchPath)
            var char = fileReader.read()
            stringBuilder = StringBuilder()
            while (char != -1) {
                stringBuilder.append(char)
                char = fileReader.read()
            }
            fileReader.close()
            result = stringBuilder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    fun copyAssetToPath(context: Context, assetPath: String, destination: String): Boolean {
        val inputStream: InputStream
        val outputStream: FileOutputStream
        var buffer = 0

        try {
            inputStream = context.assets.open(assetPath)
            outputStream = FileOutputStream(destination)
            while ({ buffer = inputStream.read(); buffer }() != -1) {
                outputStream.write(buffer)
            }

            outputStream.close()
            inputStream.close()

        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }
}