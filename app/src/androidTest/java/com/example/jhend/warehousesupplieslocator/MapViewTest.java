package com.example.jhend.warehousesupplieslocator;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.AttributeSet;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.KeyStore;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jhend on 1/5/2018.
 */
@RunWith(AndroidJUnit4.class)
public class MapViewTest {

    @Test
    public void testMapView(){
        Context context = InstrumentationRegistry.getTargetContext();
        /*
        MapView mapView = new MapView(context);
        assertEquals(100.0f, mapView.viewHeight(), .2);
        assertEquals(100.0f, mapView.viewWidth(), .2);
        assertEquals(0.0f, mapView.xPos(), .2);
        assertEquals(0.0f, mapView.yPos(), .2);
        */
    }
}
