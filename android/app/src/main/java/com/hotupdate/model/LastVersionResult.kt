package com.hotupdate.model


/**
 * Created by ImL1s on 2018/4/27.
 * Description:
 */
class LastVersionResult {

    var platform: String? = null

    var canUpdate: Boolean? = null

    var patchURL: String? = null

    var needUpdate: Boolean? = null


    override fun toString(): String {
        return "${this::class.simpleName} [" +
                "platform = $platform, " +
                "canUpdate = $canUpdate, " +
                "patchURL = $patchURL, " +
                "needUpdate = $needUpdate]"
    }
}