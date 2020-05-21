package com.example.ruchir.firstproject;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedReference {
    SharedPreferences SharedRef;
    public SharedReference(Context context){
        SharedRef=context.getSharedPreferences("myloc",context.MODE_PRIVATE);

    }
    public void SaveData(String Longitude,String Latitude){
        SharedPreferences.Editor editor=SharedRef.edit();
        editor.putString("Longitude",Longitude);
        editor.putString("Latitude",Latitude);
        editor.commit();
    }

    public String LoadData(){
        String FileContent="Longitude"+SharedRef.getString("Longitude","No Data");
        FileContent+=",Latitude"+SharedRef.getString("Latitude","No Data");
        return FileContent;
    }
}
