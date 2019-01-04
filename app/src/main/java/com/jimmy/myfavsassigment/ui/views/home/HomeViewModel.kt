package com.jimmy.myfavsassigment.ui.views.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.jimmy.myfavsassigment.businesslogic.models.AnimeObj
import com.jimmy.myfavsassigment.businesslogic.repository.AnimeRepository
import com.jimmy.myfavsassigment.ui.extensions.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {


    var animeRepository : AnimeRepository = AnimeRepository()

    // loding display indicator binder feild
    val isLoading = ObservableField(false)

    var repositories = MutableLiveData< ArrayList<AnimeObj> >()
    var error = MutableLiveData<Boolean>()

    private val compositeDisposable = CompositeDisposable()

    init {
        error.value = false
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
    }


    override fun onCleared() {
        super.onCleared()

        if(!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
    }

}
