package com.jimmy.myfavsassigment.application

import android.app.Application
import com.jimmy.myfavsassigment.dagger.AppComponent
import com.jimmy.myfavsassigment.dagger.AppModule
import com.jimmy.myfavsassigment.dagger.DaggerAppComponent

class FavApplicationClass : Application() {

    lateinit var favAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        //    This initializes the wikiComponent field when the application first starts up.
        favAppComponent = initDagger(this)
    }

    // initialize AppComponent
    private fun initDagger(app: FavApplicationClass): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}