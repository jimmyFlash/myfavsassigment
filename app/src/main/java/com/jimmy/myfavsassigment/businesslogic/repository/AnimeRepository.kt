package com.jimmy.myfavsassigment.businesslogic.repository

import com.jimmy.myfavsassigment.businesslogic.models.AnimeObj
import io.reactivex.Observable

class AnimeRepository {

    val animeDataSource = AnimeData()

    fun getRepositories() : Observable<ArrayList<AnimeObj>> {

        return animeDataSource.getRepositories()
    }


}