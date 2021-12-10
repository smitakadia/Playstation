package com.example.playstation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.playstation.model.PlayStationDetailsModel
import com.example.playstation.network.CustomApiCallback
import com.example.playstation.network.WebServiceClient
import com.example.playstation.utils.Constants

class PlayStationDetailsViewModel : ViewModel() {

    private lateinit var userListResponse: MutableLiveData<PlayStationDetailsModel>

    fun PlayStationDetails(id: String,key:String): MutableLiveData<PlayStationDetailsModel>? {
        userListResponse = getDetails(id,key)
        return userListResponse
    }

    private fun getDetails(id: String,key:String): MutableLiveData<PlayStationDetailsModel> {

        val orderListResponseModel = MutableLiveData<PlayStationDetailsModel>()

        WebServiceClient.getService()?.getDetails(id,key)
            ?.enqueue(object : CustomApiCallback<PlayStationDetailsModel>() {

                override fun handleResponseData(data: PlayStationDetailsModel?) {
                    orderListResponseModel.value = data
                }

                override fun showErrorMessage(errorMessage: String?) {
                    Log.e(Constants.WEB_FAIL_ERROR, errorMessage.toString())
                    orderListResponseModel.value = null
                }
            })

        return orderListResponseModel
    }
}