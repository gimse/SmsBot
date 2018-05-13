package no.gimse.haakon.smsbot;

import android.telephony.SmsManager;


/**
 * Copyright 2018, Hakon Gimse, All rights reserved.
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
