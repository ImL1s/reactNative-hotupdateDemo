package com.hotupdate


/**
 * Created by ImL1s on 2018/4/24.
 * Description:
 */

//const val BROADCAST_TOAST = "broadcast_toast"

const val ACTION_NATIVE_RECEIVER = "action_native_receiver"

enum class BroadcastSignal {
    SIGNAL,
    TOAST,
    TOAST_CONTENT
}

enum class Tag(val value: String){
    DEBUG("D/hotUpdate"),
    ERROR("E/hotUpdate")
}