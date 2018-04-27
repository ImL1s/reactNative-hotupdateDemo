package com.hotupdate.extension

import android.util.Log


/**
 * Created by ImL1s on 2018/4/27.
 * Description:
 */

fun Any.logD(tag: String): Any {
    Log.d(tag, this.toString())
    return this
}