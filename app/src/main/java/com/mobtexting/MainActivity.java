package com.mobtexting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import mobtexting.com.voiceandroid.Mobtexting;
import mobtexting.com.voiceandroid.MobtextingInterface;
import mobtexting.com.voiceandroid.ServerResponse;

public class MainActivity extends AppCompatActivity implements MobtextingInterface{
    private Mobtexting mobtexting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobtexting=new Mobtexting(this);
        mobtexting.clickToCall("8033931750","8553007070","7250705072",this);
    }

    @Override
    public void onResponse(ServerResponse serverResponse) {
        Log.d("success",serverResponse.getResponseCode()+"    "+serverResponse.getMessage()+"  "
        +serverResponse.getStatus());
    }

    @Override
    public void onError(ServerResponse serverResponse) {
        Log.d("failure",serverResponse.getResponseCode()+" "+serverResponse.getMessage()+"  "+
        serverResponse.getStatus());
    }
}
