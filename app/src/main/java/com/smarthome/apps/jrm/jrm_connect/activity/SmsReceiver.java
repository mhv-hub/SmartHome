package com.smarthome.apps.jrm.jrm_connect.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.smarthome.apps.jrm.jrm_connect.activity.MainActivity;

public class SmsReceiver extends BroadcastReceiver{

    String str = "";
    String sender="";
    MainActivity main = new MainActivity();
    static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ACTION)) {
            Bundle bundle = intent.getExtras();
            SmsMessage msgs = null;
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdus.length; i++) {
                    msgs = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    str += msgs.getMessageBody().toString();
                }
                sender = msgs.getOriginatingAddress().toString();
                sendToMain(context, intent);
            }
        }
    }

    public void sendToMain(Context context,Intent intent){
        //Toast.makeText(context,"Message ok "+str, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(context,MainActivity.class);
        i.putExtra("code",str);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP|intent.FLAG_ACTIVITY_NO_HISTORY| Intent.FLAG_ACTIVITY_SINGLE_TOP|intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        context.startActivity(i);
    }
}
