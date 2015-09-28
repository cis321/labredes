package com.labredes.grupo15.lab6redes;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * Created by cis on 28/09/15.
 */
public class LocartorService extends Service implements LocationListener {

    private final Context ctx;
    Location location;
    boolean gpsActive;
    LocationManager locationManager;

    public LocartorService (Context c){

        super();
        this.ctx = c;
        getLocation();
    }

    public Location getLocationObject(){
        return location;
    }

    public void getLocation(){
        try{
            locationManager = (LocationManager) this.ctx.getSystemService(LOCATION_SERVICE);
            gpsActive = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }catch (Exception e){

        }

        if (gpsActive){
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 800, 5, this);

            location = getLastKnownLocation2();

        }
    }

    private Location getLastKnownLocation2() {
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = locationManager.getLastKnownLocation(provider);

            if (l == null) {
                continue;
            }
            if (bestLocation == null
                    || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }
        if (bestLocation == null) {
            return null;
        }
        return bestLocation;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
