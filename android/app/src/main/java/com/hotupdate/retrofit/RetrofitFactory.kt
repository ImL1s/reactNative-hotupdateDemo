package com.hotupdate.retrofit

import com.google.gson.GsonBuilder
import com.hotupdate.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by ImL1s on 2018/4/27.
 * Description:
 */
object RetrofitFactory {

    // region [backing fields]
    private var _retrofit: Retrofit? = null
    // endregion


    // region[prop]
    val retrofit: Retrofit
        get() {
            if (_retrofit == null) {
                GsonBuilder()
                        .setPrettyPrinting()

                _retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return _retrofit!!
        }
    // endregion

    fun <T> getService(service: Class<T>): T {
        return retrofit.create(service)
    }
}