package com.example.playstation.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.playstation.MyApplication.Companion.COMMON_KEY
import com.example.playstation.R
import com.example.playstation.databinding.ActPlaystationListBinding
import com.example.playstation.model.PlaystationListModel
import com.example.playstation.ui.adapter.MainCategoryAdapter
import com.example.playstation.utils.Constants
import com.example.playstation.utils.RecyclerViewPaginationCallback
import com.example.playstation.viewmodel.PlayStationViewModel
import com.social.kotlinstructure.ui.common.ActBase

class ActPlayStationList : ActBase<ActPlaystationListBinding>() {

    private lateinit var userViewModel: PlayStationViewModel
    private lateinit var mainCategoryAdapter: MainCategoryAdapter
    private var pageCount = 1;
    private var pageSize = "10";
    private lateinit var callback: RecyclerViewPaginationCallback
    val userList: MutableList<PlaystationListModel.Data> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.act_playstation_list)
        init();

          /*call back for recyclerview scroll*/
        callback = object : RecyclerViewPaginationCallback(
            mBinding.rvUser,
            AXIS.VERTICAL
        ) {
            override fun paginate() {

                pageCount = pageCount + 1
                webCallGetCategoryList(true)

            }
        }


    }

    /*listener to get click on recycler view*/
    val listener = object : MainCategoryAdapter.CustomViewHolderListener {
        override fun onCustomItemClicked(x: PlaystationListModel.Data) {

            val intent = Intent(
                this@ActPlayStationList,
                ActDetails::class.java
            )
            intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
            intent.putExtra(Constants.PLATSTATION_KEY, x.id.toString())
            startActivity(intent)


        }

    }


    private fun init() {
        userViewModel = ViewModelProviders.of(this).get(PlayStationViewModel::class.java)

        webCallGetCategoryList(false)
    }

    /*call api to get play list*/
    private fun webCallGetCategoryList(isloadmore: Boolean) {
        if(!isloadmore)
        showLoader(this)
        userViewModel.playstationList(pageCount.toString(), pageSize, "released", COMMON_KEY)
            ?.observe(this@ActPlayStationList, Observer { resultModel ->



                if (resultModel == null) {
                    showErrorAlert(
                        getString(R.string.string_web_response_null),
                        "Something went wrong"
                    )

                    return@Observer
                }

                   /*get data from web services*/


                resultModel.results?.let { userList.addAll(it) }


                   /*set adpter*/
                if (isloadmore) {
                    mainCategoryAdapter.notifyDataSetChanged()

                    callback.unlock()
                } else {
                    mainCategoryAdapter =
                        MainCategoryAdapter(this@ActPlayStationList, userList, listener)
                    mBinding.rvUser.adapter = mainCategoryAdapter

                    callback.updateCondition(userList.isNotEmpty())
                }

                 dismissLoader()

                showNoData(
                    mBinding.rvUser,
                    mBinding.noData,
                    resultModel.results.isNullOrEmpty(),
                    resources.getString(R.string.string_no_data_found)
                )

            })
    }



}