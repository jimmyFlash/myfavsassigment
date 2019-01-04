package com.jimmy.myfavsassigment;

import com.jimmy.myfavsassigment.businesslogic.models.AnimeObj;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SampleJimUniyTest {

    private AnimeObj aniObj;

    @Before
    public void setUp() throws Exception {

        aniObj = new AnimeObj("Test", 5);

    }

    @Test
    public void TestObjectName ()throws Exception{

        assertEquals("Test", aniObj.getAnimeName());

    }

    @Test
    public void TestObjectStar ()throws Exception{

        int val = aniObj.getNumberOfStars();
        assertEquals(5, val);

    }
}
