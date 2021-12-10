package com.example.playstation.network

import com.example.playstation.model.PlayStationDetailsModel
import com.example.playstation.model.PlaystationListModel
import retrofit2.Call
import retrofit2.http.*


interface WebServiceAPI {



    @GET("games")
    fun getplaystationList(@Query("page") pageNo: String,@Query("page_size") pageSize: String,@Query("ordering") ordering: String,@Query("key") key: String): Call<PlaystationListModel>

    @GET("games/{input}")
    fun getDetails(@Path("input")  input : String, @Query("key") pageNo: String,): Call<PlayStationDetailsModel>


}