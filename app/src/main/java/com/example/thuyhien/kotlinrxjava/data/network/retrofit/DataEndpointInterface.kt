package com.example.thuyhien.kotlinrxjava.data.network.retrofit

import com.example.thuyhien.kotlinrxjava.model.StoreCoupons
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by thuyhien on 3/21/18.
 */
interface DataEndpointInterface {
    @GET("coupons/")
    fun getStoreCoupons(@Query("status") status: String) : Observable<StoreCoupons>

    @GET("storeOffers/")
    fun getStoreInfo() : Observable<StoreCoupons>
}