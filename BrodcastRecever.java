package com.example.ruchir.firstproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class BrodcastRecever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        if(intent.getAction().equalsIgnoreCase("android.intent.action.BOOT_COMPLETED")){
            myLocationListener myLocationListener = new myLocationListener(context);
            LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, myLocationListener);

        }
        if(intent.getAction().equalsIgnoreCase("android.provider.Telephony.SMS_RECEIVED")){

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdusObj.length];
                for (int i = 0; i < messages.length; i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = bundle.getString("format");
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
                    }
                    else {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    }
                    // SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String senderNum =messages[i].getOriginatingAddress();
                    String message =messages[i].getMessageBody();//
                    PhnoShared phnoshred= new PhnoShared(context);
                    String Trno=phnoshred.LoadData(1);
                    String SenNo=phnoshred.LoadData(2);

                    if(senderNum.equalsIgnoreCase(Trno) )
                    {

                        SharedReference shredref=new SharedReference(context);
                        String deta=shredref.LoadData();

                        SmsManager smsManagersend = SmsManager.getDefault();
                        smsManagersend.sendTextMessage(SenNo, null,"This student is at location "+deta, null, null);
                        Toast.makeText(context,"Message Sent To"+SenNo,Toast.LENGTH_LONG).show();


                    }
                }

            }

        }
    }

}
