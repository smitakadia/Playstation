package com.example.playstation.ui.activity

import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.playstation.MyApplication
import com.example.playstation.R
import com.example.playstation.databinding.ActDetailsBinding
import com.example.playstation.model.PlayStationDetailsModel
import com.example.playstation.utils.Constants
import com.example.playstation.viewmodel.PlayStationDetailsViewModel
import com.social.kotlinstructure.ui.common.ActBase

class ActDetails : ActBase<ActDetailsBinding>() {

    private lateinit var playStationDetails: PlayStationDetailsViewModel
    private lateinit var playStationId :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.act_details)
        init();
    }

    private fun init() {
        getIntentData()
        playStationDetails = ViewModelProviders.of(this).get(PlayStationDetailsViewModel::class.java)
     /*call api to get playstation details*/
        webCallGetPlaystationDetails()
    }

    private fun getIntentData() {
        playStationId = intent.getStringExtra(Constants.PLATSTATION_KEY).toString()
    }


    /*api to get playstation details*/
    private fun webCallGetPlaystationDetails() {
        showLoader(this)
        playStationDetails.PlayStationDetails(playStationId, MyApplication.COMMON_KEY)

            ?.observe(this@ActDetails, Observer { resultModel ->

                dismissLoader()

                if (resultModel == null) {
                    showErrorAlert(
                        getString(R.string.string_web_response_null),
                        "Something went wrong"
                    )
                    return@Observer
                }

            /*get data from web services*/
                var details: MutableList<PlayStationDetailsModel> = ArrayList()


                details.clear()
                details.add(resultModel)
                mBinding.mdl = details.get(0)
                mBinding.nestedScrollview.visibility = View.VISIBLE

                mBinding.tvDesc.text = Html.fromHtml(details.get(0).description)
                if(details.get(0).imageBackground != null)
                    Glide.with(mBinding.ivImage.context).load(Uri.parse(details.get(0).imageBackground)).error(R.drawable.ic_placeholder).into(mBinding.ivImage)

                if(details.get(0).genres != null){
                    for (i in 0 until details.get(0).genres!!.size) {
                           mBinding.tvGenere.text = mBinding.tvGenere.text.toString() + details.get(0).genres!!.get(i).name + " "
                    }
                }

            })
    }


}