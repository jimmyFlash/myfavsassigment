package com.jimmy.myfavsassigment;

import com.jimmy.myfavsassigment.businesslogic.models.AnimeObj;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

public class RxJavaWithTestObserver {
    private ArrayList<AnimeObj> animeSetList = new ArrayList<AnimeObj>();

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

    @Test
    public void assert_anime_w_TO () {

        TestObserver<ArrayList<AnimeObj>> subscriber = new TestObserver<>();
        getRepositories().subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertValueCount(1);


    }
}
