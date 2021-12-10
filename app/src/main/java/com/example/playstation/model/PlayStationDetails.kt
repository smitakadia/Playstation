package com.example.playstation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class PlayStationDetailsModel  : CommonResponseModel() {


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
    var metacritic : Int? = 0

    @SerializedName("updated")
    @Expose
    var updated :String? = null

    @SerializedName("suggestions_count")
    @Expose
    var suggestions_count: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("website")
    @Expose
    var website: String? = null

    @SerializedName("playtime")
    @Expose
    var playtime: Int? = null

    @SerializedName("achievements_count")
    @Expose
    var achievement_count: Int? = null

    @SerializedName("genres")
    @Expose
    var genres: List<Genre>? = null


}

class Genre{
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("games_count")
    @Expose
    var gamesCount: Int? = null

    @SerializedName("image_background")
    @Expose
    var imageBackground: String? = null
}
