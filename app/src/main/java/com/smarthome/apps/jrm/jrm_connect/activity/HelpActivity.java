package com.smarthome.apps.jrm.jrm_connect.activity;


import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.TextView;

import com.smarthome.apps.jrm.jrm_connect.R;

import org.w3c.dom.Text;

public class HelpActivity extends AppCompatActivity{

    TextView titlehelp,tip1,tip2,tip3,tip4,tip5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_help);

        titlehelp = (TextView) findViewById(R.id.titlehelp);
        titlehelp.setTextColor(getResources().getColor(R.color.whiteColor));

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/runningshoe.ttf");
        tip1 = (TextView) findViewById(R.id.tip1);
        tip1.setTypeface(custom_font);

        tip2 = (TextView) findViewById(R.id.tip2);
        tip2.setTypeface(custom_font);
        tip3 = (TextView) findViewById(R.id.tip3);
        tip3.setTypeface(custom_font);
        tip4 = (TextView) findViewById(R.id.tip4);
        tip4.setTypeface(custom_font);
        tip5 = (TextView) findViewById(R.id.tip5);
        tip5.setTypeface(custom_font);

        tip1.setTextColor(getResources().getColor(R.color.semidark));
        tip2.setTextColor(getResources().getColor(R.color.semidark));
        tip3.setTextColor(getResources().getColor(R.color.semidark));
        tip4.setTextColor(getResources().getColor(R.color.semidark));
        tip5.setTextColor(getResources().getColor(R.color.semidark));

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.6));

    }
}
