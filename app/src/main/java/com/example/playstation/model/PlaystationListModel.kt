package com.example.playstation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class PlaystationListModel  : CommonResponseModel() {

    @SerializedName("results")
    @Expose
    var results: MutableList<Data>? = null
  //  var userList: MutableList<User>? = null


    class Data {

        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("slug")
        @Expose
        var slug: String? = null

        @SerializedName("name")
        @Expose
        var name : String? = null

        @SerializedName("background_image")
        @Expose
        var imageBackground: String? = null


        @SerializedName("metacritic")
        @Expose
        var metacritic : Int? = null

        @SerializedName("updated")
        @Expose
        var updated :String? = null

        @SerializedName("suggestions_count")
        @Expose
        var suggestions_count: String? = null









    }
}
