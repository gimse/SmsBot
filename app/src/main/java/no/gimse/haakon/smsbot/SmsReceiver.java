package no.gimse.haakon.smsbot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


public class SmsReceiver extends BroadcastReceiver {

    private MainProgram mainprogram;
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();


    public void setMainprogram(MainProgram mainprogram){
        this.mainprogram=mainprogram;
    }

    public void onReceive(Context context, Intent intent) {
        Log.i("SmsReceiver","Melding motatt");
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String messageReceived = "";
        if (bundle != null) {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++)

            {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                messageReceived += msgs[i].getMessageBody().toString();
                messageReceived += "\n";
            }
        }
        //Toast.makeText(context, messageReceived, Toast.LENGTH_SHORT).show();
        String senderPhoneNumber=msgs[0].getOriginatingAddress ();
        mainprogram.useMessage(messageReceived,senderPhoneNumber);

    }

}