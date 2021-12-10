package com.example.playstation.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.playstation.R
import com.example.playstation.databinding.ActSplashBinding
import com.social.kotlinstructure.ui.common.ActBase


class ActSplash : ActBase<ActSplashBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.act_splash)

        openMainScreen()
    }

    private fun openMainScreen() {
        Handler().postDelayed(Runnable {
            val intent = Intent(
                this@ActSplash,
                ActPlayStationList::class.java
            )
            intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
            startActivity(intent)
            finish()
        }, 3500)
    }
}