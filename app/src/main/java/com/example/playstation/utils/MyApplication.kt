package com.example.playstation

import android.app.Application
import android.content.Context
import com.example.playstation.network.WebServiceClient

class MyApplication:Application() {
    companion object {
        var BASE_URL  : String = "https://api.rawg.io/api/"
        var COMMON_KEY : String = "02ef6ba5d13444ee86bad607e8bce3f4"

        lateinit  var appContext: Context

    }


    override fun onCreate() {
        super.onCreate()

        // Initialize API Client
        WebServiceClient.init(this)
    }
}