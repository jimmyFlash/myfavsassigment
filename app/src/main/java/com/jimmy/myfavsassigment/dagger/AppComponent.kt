package com.jimmy.myfavsassigment.dagger

import com.jimmy.myfavsassigment.ui.views.home.HomeViewModel
import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
/**
 * told Dagger that AppComponent is a singleton component interface for the app
 */


@Component(modules = [AppModule::class, RespositoryModule::class])
/**
 * The @Component annotation takes a list of modules as an input.
 * You’re using the literal array syntax available in Kotlin
 */
interface AppComponent {
    /**
     * The component is used to connect objects to their dependencies,
     * typically by use of overridden inject() methods.
     * In order to use the component, it must be accessible from the parts of the app that
     * need injection. Typically, that will happen from the app Application subclass
     */

    //specified that the HomeViewModel class will require injection from AppComponent.
    fun inject (homeViewModel: HomeViewModel)

}