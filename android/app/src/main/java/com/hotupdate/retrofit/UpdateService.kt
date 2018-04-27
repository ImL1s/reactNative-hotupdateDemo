package com.hotupdate.retrofit

import com.hotupdate.model.LastVersionResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by ImL1s on 2018/4/27.
 * Description:
 */
interface UpdateService {

    @GET("/version")
    fun checkLastVersion(@Query("bundleVersion") bundleVersion: String,
                         @Query("nativeVersion") nativeVersion: String,
                         @Query("platform") platform: String = "android")
            : Observable<LastVersionResult>
}