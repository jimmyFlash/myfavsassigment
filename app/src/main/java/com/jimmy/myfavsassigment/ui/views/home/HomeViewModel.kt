package com.jimmy.myfavsassigment.ui.views.home

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.os.Handler
import com.jimmy.myfavsassigment.application.FavApplicationClass
import com.jimmy.myfavsassigment.businesslogic.models.AnimeObj
import com.jimmy.myfavsassigment.businesslogic.repository.AnimeRepository
import com.jimmy.myfavsassigment.ui.extensions.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class HomeViewModel (application : Application) : AndroidViewModel(application) {



    @Inject
    lateinit var animeRepository: AnimeRepository

    // loding display indicator binder feild
    val isLoading = ObservableField(false)
    val shuffleOn = MutableLiveData<Boolean>()

    var repositories = MutableLiveData< ArrayList<AnimeObj> >()
    var error = MutableLiveData<Boolean>()

    private val compositeDisposable = CompositeDisposable()


    init {
        error.value = false
        shuffleOn.value = false

        /*
     You are getting the AppComponent from WikiApplication and asking it to inject all known
     dependencies into HomepageActivity. Since you annotated presenter with @Inject,
     Dagger will inject a concrete HomepagePresenter object into HomepageActivity

     Dagger knows that you defined provideHomepagePresenter() in the PresenterModule class,
      and uses it to create the injected HomepagePresenter object.
    */
        (application as FavApplicationClass).favAppComponent.inject(this)

    }


    /**
     * load / update the list of anime repo
     */
    fun loadRepositories() {
        isLoading.set(true)


        compositeDisposable += animeRepository.getRepositories()// load the repositories, retuns RxJava Observable
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<ArrayList<AnimeObj>>() {

                // If some error happens onError() will be called and after that Observable terminates
                override fun onError(e: Throwable) {
                    //if some error happens in our data layer our app will not crash, we will
                    // get error here
                    error.value = true
                }

                // Every time when Observable emits the data onNext() method will be called.
                override fun onNext(data: ArrayList<AnimeObj>) {
                    data.sortWith(compareBy<AnimeObj> { it.numberOfStars })
                    data.reverse()
                    repositories.value = data
                }

                //Once when Observable finish with emitting onComplete() will be called
                //After that Observable terminates
                override fun onComplete() {
                    isLoading.set(false)
                }
            })

    }

    fun refreshRepositories(position: Int, rating: Int) {

        animeRepository.updateRepStarRating(position, rating)
        loadRepositories()
    }


    private lateinit var mHandler: Handler

    private lateinit var runner: Runnable

    fun randomRateRepo(){

        if(shuffleOn.value == false){
            shuffleOn.value = true
            shuff()
        }else{
            shuffleOn.value = false
            mHandler.removeCallbacks(runner)
        }
    }

    fun shuff(){

        val rand = Random()
        val shuffleInterval = rand.nextInt(5000) + 1000
        val star = rand.nextInt(4)
        var itemPos = repositories.value?.size?.let { rand.nextInt(it) }
        if(itemPos!! >= repositories.value?.size!!){
            itemPos = 0
        }


        refreshRepositories(itemPos!!, star)

        mHandler = Handler()
        runner = Runnable { shuff() }
        mHandler.postDelayed(runner, shuffleInterval.toLong())

    }


    override fun onCleared() {
        super.onCleared()

        if(!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }

}
