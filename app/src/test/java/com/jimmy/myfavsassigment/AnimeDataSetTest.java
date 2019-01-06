package com.jimmy.myfavsassigment;

import com.jimmy.myfavsassigment.businesslogic.models.AnimeObj;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class AnimeDataSetTest {

    private ArrayList<AnimeObj> animeSetList = new ArrayList<AnimeObj>();
    private String subAnimeName = "Resident evil";
    private int subAnimeStar = 3;

    @Before
    public void setUp() {

        animeSetList.add(new AnimeObj("Attack on titan", 5));
        animeSetList.add(new AnimeObj("Tokyo ghoul", 4));
        animeSetList.add(new AnimeObj("Sword art online",  3));
        animeSetList.add(new AnimeObj("Claymore", 2));
        animeSetList.add(new AnimeObj("Death note",  1));

    }


    private Observable<ArrayList<AnimeObj>> getRepositories() {

        return Observable.just(animeSetList);
    }

    private void updateAnimeRating(int pos , int stars){

        AnimeObj animeObj = animeSetList.get(pos);
        animeObj.setNumberOfStars(stars);
    }

    private void updateAnimeName(int pos , String nm){

        AnimeObj animeObj = animeSetList.get(pos);
        animeObj.setAnimeName(nm);
    }

    @Test
    public void assert_anime_list_length () {

        assertEquals(5, animeSetList.size());

    }

    @Test
    public void assert_anime_name_update () {

        updateAnimeName(animeSetList.size()-1 , subAnimeName);

        assertEquals("Resident evil", animeSetList.get(animeSetList.size()-1).getAnimeName());

    }

    @Test
    public void assert_anime_rating_update () {

        updateAnimeRating(animeSetList.size()-1 , subAnimeStar);

        int statsUp = animeSetList.get(animeSetList.size()-1).getNumberOfStars();
        assertEquals(3, statsUp);

    }
}
