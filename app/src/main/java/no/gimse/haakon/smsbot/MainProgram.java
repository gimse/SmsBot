package no.gimse.haakon.smsbot;

import android.widget.Toast;

import chatterbotapi.ChatterBot;
import chatterbotapi.ChatterBotFactory;
import chatterbotapi.ChatterBotSession;
import chatterbotapi.ChatterBotType;

/**
 * Copyright 2018, Hakon Gimse, All rights reserved.
 */

public class MainProgram {
    private boolean running;

    private ChatterBotSession bot1session;
    private MainActivity mainActivity;
    private boolean botReady=false;
    private SmsSender smsSender;

    public MainProgram(MainActivity mainActivity, SmsSender smsSender){
        this.mainActivity=mainActivity;
        this.smsSender=smsSender;

    }

    public void useMessage(String message,String fromNumber){
        if(running) {
            if (!botReady) {
                botReady = makeBot();
                if (!botReady) {
                    dipsError("Robot making error. Check internet connection. ");
                    return;
                }

            }
            try {
                String returnMessage = bot1session.think(message);
                smsSender.sendSms(returnMessage, fromNumber);

                Toast.makeText(mainActivity.getApplicationContext(), "SMS from "+fromNumber+", answered" + returnMessage, Toast.LENGTH_SHORT).show();
                dipsError("");
            } catch (Exception e) {
                dipsError("Robot error. Check internet connection. ");
            }
        }
    }
    public boolean makeBot(){
        try {
            ChatterBotFactory factory = new ChatterBotFactory();

            ChatterBot bot1 = factory.create(ChatterBotType.JABBERWACKY);
            bot1session = bot1.createSession();
            return true;
        }catch(Exception e){
            e.printStackTrace();
                printError("Something when wrong making the bot");
            return false;
        }
    }
    public void setRunning(boolean running){
        this.running=running;
    }
    private void printError(String string){
        Toast.makeText(mainActivity.getApplicationContext(), string, Toast.LENGTH_SHORT).show();
    }
    public void dipsError(String text){
        mainActivity.dispError(text);
    }
}
