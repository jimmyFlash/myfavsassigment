package com.jimmy.myfavsassigment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import com.jimmy.myfavsassigment.ui.extensions.start
import com.jimmy.myfavsassigment.ui.views.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // remove action bar
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        // timed splash, using extion function to navigate to home activity
        Handler().postDelayed({HomeActivity::class.start(this, true)} ,2000)

    }
}
