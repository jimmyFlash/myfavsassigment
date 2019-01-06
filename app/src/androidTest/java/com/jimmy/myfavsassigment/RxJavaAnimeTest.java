package com.jimmy.myfavsassigment;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import com.jimmy.myfavsassigment.businesslogic.models.AnimeObj;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class RxJavaAnimeTest {


    private static final String TAG = RxJavaAnimeTest.class.getCanonicalName();

    private ArrayList<AnimeObj> animeSetList = new ArrayList<AnimeObj>();

    private AnimeObj testObj = new AnimeObj("Attack on titan", 5);


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
    public void test_observable_list_not_empty() throws Exception{
        Observable<ArrayList<AnimeObj>> getRep = getRepositories();
        ArrayList<AnimeObj> list = getRep.blockingSingle();
        assertThat(false, is(list.isEmpty()));
    }


    @Test
    public void test_single_observer() throws Exception{

        TestObserver<ArrayList<AnimeObj>> o = new TestObserver<ArrayList<AnimeObj>>();

        getRepositories().subscribe(o);
        o.assertValue(animeSetList);
        o.assertComplete();
        o.assertNoErrors();

    }



    @After
    public void tearDown(){

    }


}
