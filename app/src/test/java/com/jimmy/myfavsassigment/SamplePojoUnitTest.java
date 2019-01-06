package com.jimmy.myfavsassigment;

import com.jimmy.myfavsassigment.businesslogic.models.AnimeObj;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SamplePojoUnitTest {

    private AnimeObj aniObj;

    @Before
    public void setUp() {

        aniObj = new AnimeObj("Test", 5);

    }

    @Test
    public void assert_object_notnull () {

        assertNotNull("object found is ", aniObj);

    }

    @Test
    public void assert_object_name () {

        assertEquals("Test", aniObj.getAnimeName());

    }

    @Test
    public void assert_object_star_rating () {

        int val = aniObj.getNumberOfStars();
        assertEquals(5, val);

    }
}
