package com.talentjoko.leadership2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread t=new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(1500);
                    Intent intent = new Intent(getBaseContext(),TabbedActivity.class);
                    startActivity(intent);

                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();


    }
}