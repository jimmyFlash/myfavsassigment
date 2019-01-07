package com.jimmy.myfavsassigment.dagger

import com.jimmy.myfavsassigment.businesslogic.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RespositoryModule {

    @Provides
    /**
     * The @Provides annotation tells Dagger that the method provides a certain type of dependency,
     * specifying that a HomepagePresenter will be provided, and that the presenter returned will be
     * the concrete implementation HomepagePresenterImpl
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
    fun provideAnimeRepository(): AnimeRepository = AnimeRepository()

}