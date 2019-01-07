package com.jimmy.myfavsassigment.dagger

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @Module
 * The @Module annotation tells Dagger that the AppModule class will provide dependencies for a part
 * of the application. It is normal to have multiple Dagger modules in a project,
 * and it is typical for one of them to provide app-wide dependencies.
 */
@Module
class AppModule (private val app: Application){

    @Provides
    /**
     * The @Provides annotation tells Dagger that the method provides a certain type of dependency,
     * in this case, a Context object. When a part of the app requests that Dagger inject a Context,
     * the @Provides annotation tells Dagger where to find it.
     */
    @Singleton
    /**
     * The @Singleton annotation is not part of the Dagger API. It’s contained inside the javax package
     *  added to build.gradle . It tells Dagger that there should only be a single instance of that
     *  dependency. So when generating the code Dagger will handle all the logic for you,
     *  and you won’t write all the boilerplate code to check if another instance of the object
     *  is already available.
     */
    fun provideContext(): Context = app
}