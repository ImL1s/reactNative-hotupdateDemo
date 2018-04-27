package com.hotupdate.extension

import android.content.Context
import android.widget.Toast


/**
 * Created by ImL1s on 2018/4/27.
 * Description:
 */

fun Any.toastLong(context: Context): Any {
    Toast.makeText(context, this.toString(), Toast.LENGTH_LONG).show()
    return this
}

fun Any.toast(context: Context): Any {
    Toast.makeText(context, this.toString(), Toast.LENGTH_SHORT).show()
    return this
}
