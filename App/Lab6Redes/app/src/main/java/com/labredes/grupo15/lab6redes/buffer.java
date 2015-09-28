package com.labredes.grupo15.lab6redes;

import android.location.Location;

/**
 * Created by cis on 28/09/15.
 */
public class buffer {
    private static Location location;

    public static void setLocation(Location pLocation){

        location = pLocation;
    }

    public static Location getLocation(){
        return location;
    }
}
