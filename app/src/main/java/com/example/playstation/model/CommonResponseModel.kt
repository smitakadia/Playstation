package com.example.playstation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class CommonResponseModel {

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("msg")
    @Expose
    var msg: String? = null
}