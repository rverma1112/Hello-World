package com.example.ruchir.firstproject;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class myLocationListener implements LocationListener {
    public static Location location;
    public static String Longit;
    public static String Lat;

    public static boolean isRunning=false;
    Context context;
    public myLocationListener(Context context){
        isRunning=true;
        location=new Location("not defined");
        location.setLatitude(0);
        location.setLongitude(0);
        this.context=context;
    }
    @Override
    public void onLocationChanged(Location location) {
        this.location=location;
        Longit=String.valueOf(location.getLongitude());
        Lat=String.valueOf(location.getLatitude());
        String Msg="Long:"+Longit+"Lat:"+Lat;
        Toast.makeText(context,Msg,Toast.LENGTH_LONG).show();
        SharedReference sharedRef=new SharedReference(context);
        sharedRef.SaveData(Longit,Lat);


    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Toast.makeText(context, "GPS status changed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(context,"GPS is enabled",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(context,"GPS is disable",Toast.LENGTH_LONG).show();

    }
}
