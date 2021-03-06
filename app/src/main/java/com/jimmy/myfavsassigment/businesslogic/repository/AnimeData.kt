package com.jimmy.myfavsassigment.businesslogic.repository

import com.jimmy.myfavsassigment.businesslogic.models.AnimeObj
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class AnimeData {

    private var arrayList: ArrayList<AnimeObj> = ArrayList()


    init {

        arrayList.add(AnimeObj("Attack on titan", 5))
        arrayList.add(AnimeObj("Tokyo ghoul", 4))
        arrayList.add(AnimeObj("Sword art online",  2))
        arrayList.add(AnimeObj("Claymore", 5))
        arrayList.add(AnimeObj("Death note",  4))

        arrayList.add(AnimeObj("My hero academia", 5))
        arrayList.add(AnimeObj("Cells at work",  4))
        arrayList.add(AnimeObj("Goblin slayer",  2))
        arrayList.add(AnimeObj("Baki", 5))
        arrayList.add(AnimeObj("One piece",  4))

    }

    /**
     *
     */
    fun getRepositories() : Observable<ArrayList<AnimeObj>> {

        return Observable.just(arrayList)
            .delay(1, TimeUnit.SECONDS)
    }

    fun updateAnimeRating(pos : Int, stars : Int){

        val animeObj = arrayList[pos]
            animeObj.numberOfStars = stars

    }
}