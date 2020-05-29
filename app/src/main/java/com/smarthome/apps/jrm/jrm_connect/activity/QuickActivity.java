package com.smarthome.apps.jrm.jrm_connect.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.smarthome.apps.jrm.jrm_connect.R;

public class QuickActivity extends AppCompatActivity {


    Button bye,hello,gm,gn;
    MainActivity m;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_quick);

        m = new MainActivity();

        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width =  dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.85),(int)(height*.55));

        bye = (Button) findViewById(R.id.bye);
        hello = (Button) findViewById(R.id.hello);
        gm = (Button) findViewById(R.id.gm);
        gn = (Button) findViewById(R.id.gn);

        final Intent intent = new Intent();

        bye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("actionCode","RMB");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("actionCode","RMH");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        gm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("actionCode","RMM");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        gn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("actionCode","RMN");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
