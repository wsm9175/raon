package com.raon.android.raonapp.geocoder;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.raon.android.raonapp.MainApplication;

import java.util.List;
import java.util.Locale;

public class GeocoderModule {
    public static Location getLocation(String address){
        Location location;
        try{
            Geocoder geocoder = new Geocoder(MainApplication.getInstance().getApplicationContext(), Locale.KOREA);
            List<Address> addresses = geocoder.getFromLocationName(address, 1);
            location = new Location("");
            location.setLatitude(addresses.get(0).getLatitude());
            location.setLongitude(addresses.get(0).getLongitude());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return location;
    }
}
