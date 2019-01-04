package com.jimmy.myfavsassigment.ui.views.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jimmy.myfavsassigment.R

class HomeActivity : AppCompatActivity(), RatingDialogFrag.ClickHandler {


    companion object {
        private lateinit var homefraf: HomeFragment
    }

    override fun onItemClick(position: Int, rating: Int) {
        homefraf?.updateItemRating(position, rating)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        if (savedInstanceState == null) {
            homefraf = HomeFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }
    }

}
