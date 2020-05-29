package com.smarthome.apps.jrm.jrm_connect.activity;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.smarthome.apps.jrm.jrm_connect.R;

import java.util.Hashtable;

public class SettingsAcivity extends AppCompatActivity{

    Button setbut1,setbut2,setbut3,setbut4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_settings);


        setbut1 = (Button) findViewById(R.id.settingsbutton1);
        setbut2 = (Button) findViewById(R.id.settingsbutton2);
        setbut3 = (Button) findViewById(R.id.settingsbutton3);
        setbut4 = (Button) findViewById(R.id.settingsbutton4);




    }
}
