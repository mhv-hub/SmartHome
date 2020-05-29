package com.smarthome.apps.jrm.jrm_connect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarthome.apps.jrm.jrm_connect.R;

public class ContactActivity extends AppCompatActivity {

    TextView titlecontact;
    ImageView logo;
    int c = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_contact);

        titlecontact = (TextView) findViewById(R.id.titlecontact);
        titlecontact.setTextColor(getResources().getColor(R.color.whiteColor));

        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.75));

        logo = (ImageView) findViewById(R.id.unit);

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c++;
                if(c%3==0)
                {
                    Intent i = new Intent(ContactActivity.this,EntryClass.class);
                    startActivity(i);
                }

            }
        });


    }
}
