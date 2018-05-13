package no.gimse.haakon.smsbot;

import android.content.Context;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

/**
 * Created by lefdal on 22.09.2016.
 */

public class SmsSender {
    MainProgram program;
    public SmsSender(MainProgram program){
        this. program= program;
    }


    public boolean sendSms(String sms,String phoneNo){


        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, sms, null, null);
            return true;
        } catch (Exception e) {
            program.dipsError("Failed to send SMS.");
            e.printStackTrace();
            return false;
        }
    }
}
