package  com.example.playstation.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginRequestModel {

    @SerializedName("email")
    @Expose
    var email: String = "eve.holt@reqres.in"

    @SerializedName("password")
    @Expose
    var password: String = "cityslicka"

}

class LogoutRequestModel {

    @SerializedName("userId")
    @Expose
    var userId: String? = null

}