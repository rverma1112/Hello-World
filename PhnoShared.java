package com.example.ruchir.firstproject;

import android.content.Context;
import android.content.SharedPreferences;

public class PhnoShared {
    SharedPreferences shredpref;
    public PhnoShared(Context context){
        shredpref=context.getSharedPreferences("phnono",context.MODE_PRIVATE);

    }
    public void SaveData(String PhNumber, String SendPhno){
        SharedPreferences.Editor editor=shredpref.edit();
        editor.putString("Phone Number",PhNumber);
        editor.putString("Teacher Phone Number", SendPhno);

        editor.commit();
    }

    public String LoadData(int a){
        if (a==1){
        String FileContent=shredpref.getString("Phone Number","No Data");
        return FileContent;}
        else{
            String FileContent=shredpref.getString("Teacher Phone Number","No Data");
            return FileContent;
        }
    }
}
