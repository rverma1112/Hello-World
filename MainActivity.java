package com.example.ruchir.firstproject;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckUserPermsions();
    }

    void CheckUserPermsions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) !=
                    PackageManager.PERMISSION_GRANTED)&& (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
                    PackageManager.PERMISSION_GRANTED)
                    ) {
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }

        // init the contact list
        StartServices();

    }

    //get acces to location permsion
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // init the contact list
                    StartServices();
                } else {
                    // Permission Denied
                    Toast.makeText(this, "your message", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    void StartServices(){
        //start tracking location
        if(!myLocationListener.isRunning)
        {
            myLocationListener myLocationListener = new myLocationListener(this);
            LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, myLocationListener);
            SharedReference sharedRef=new SharedReference(this);
            String Longit=myLocationListener.Longit;
            String Lat=myLocationListener.Lat;
            sharedRef.SaveData(Longit,Lat);
        }
    }


    public void phSave(View view) {
        EditText edtext=findViewById(R.id.edtext);
        EditText edtext2=findViewById(R.id.edtext2);
        PhnoShared phshred=new PhnoShared(this);
        phshred.SaveData(edtext.getText().toString(),edtext2.getText().toString());
        Toast.makeText(this,"Number Saved",Toast.LENGTH_LONG).show();
    }
}
