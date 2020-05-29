package com.smarthome.apps.jrm.jrm_connect.activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


import com.smarthome.apps.jrm.jrm_connect.R;

import org.w3c.dom.Text;

import java.util.Random;

public class UsageActivity extends AppCompatActivity{

    SQLiteDatabase db;
    Random r;
    String s;
    int bat;
    int flage = 0;
    int pumpread,powerread;
    int pmin,pmax,pwrmin,pwrmax,mid1pwr,mid2pwr;
    ImageView ind;

    TextView titlepower,powerlevel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_usages);

        db = openOrCreateDatabase("Unit_database", Context.MODE_PRIVATE,null);
        ind  = (ImageView) findViewById(R.id.ind);

        try {
            Cursor c = db.rawQuery("SELECT * FROM data", null);
            c.moveToFirst();
            s = c.getString(0);
            pmin = Integer.parseInt(c.getString(0));
            pmax = Integer.parseInt(c.getString(1));
            pwrmin = Integer.parseInt(c.getString(2));
            pwrmax = Integer.parseInt(c.getString(3));
            mid1pwr = Integer.parseInt(c.getString(4));
            mid2pwr = Integer.parseInt(c.getString(5));
        }catch(Exception e){
            flage = 1;
        }
            try {
                Cursor c1 = db.rawQuery("SELECT * FROM reading", null);
                c1.moveToFirst();
                pumpread = Integer.parseInt(c1.getString(0));
                powerread = Integer.parseInt(c1.getString(1));
            }catch(Exception e){
                flage = 1;
        }
        if(flage == 1 || s==null){
            try {
                powerlevel.setText(powerread);
            }catch(Exception e){}
        }
        else{
            try {
                powerlevel.setText(powerread);

                if (powerread <= mid1pwr && powerread >= pwrmin) {
                    ind.setImageResource(R.drawable.redindicator);
                }

                if (powerread <= mid2pwr && powerread >= mid1pwr) {
                    ind.setImageResource(R.drawable.orangeindicator);
                }

                if (powerread <= pwrmax && powerread >= mid1pwr) {
                    ind.setImageResource(R.drawable.greenindicator);
                }
            }catch(Exception e){}
        }
        try {
            powerlevel.setText(powerread);
        }catch(Exception e){}

        DisplayMetrics dm = new DisplayMetrics();


        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.4));


        powerlevel = (TextView) findViewById(R.id.powerlevel);
        powerlevel.setTextColor(getResources().getColor(R.color.whiteColor));

        titlepower = (TextView) findViewById(R.id.titlepower);
        titlepower.setTextColor(getResources().getColor(R.color.whiteColor));

        r = new Random();
        bat = r.nextInt(100);
        bat = bat+180;
        powerlevel.setText(bat+" V");

    }
}
