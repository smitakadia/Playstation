package com.example.playstation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.playstation.model.PlaystationListModel
import com.example.playstation.network.CustomApiCallback
import com.example.playstation.network.WebServiceClient
import com.example.playstation.utils.Constants

class PlayStationViewModel : ViewModel() {

    private lateinit var userListResponse: MutableLiveData<PlaystationListModel>
    private val pageSize = 5




    fun playstationList(pageNo: String,pageSize : String ,ordering :String,key : String): MutableLiveData<PlaystationListModel>? {
        userListResponse = getplaystationList(pageNo,pageSize,ordering,key)
        return userListResponse
    }

    private fun getplaystationList(pageNo: String,pageSize : String ,ordering :String,key : String): MutableLiveData<PlaystationListModel> {

        val orderListResponseModel = MutableLiveData<PlaystationListModel>()

        WebServiceClient.getService()?.getplaystationList(pageNo,pageSize,ordering,key)
            ?.enqueue(object : CustomApiCallback<PlaystationListModel>() {

                override fun handleResponseData(data: PlaystationListModel?) {
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