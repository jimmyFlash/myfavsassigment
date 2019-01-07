package com.jimmy.myfavsassigment.application

import android.app.Application
import com.jimmy.myfavsassigment.dagger.AppComponent
import com.jimmy.myfavsassigment.dagger.AppModule
import com.jimmy.myfavsassigment.dagger.DaggerAppComponent

class FavApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    // initialize AppComponent
    private fun initDagger(app: FavApplicationClass): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}