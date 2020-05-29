package com.smarthome.apps.jrm.jrm_connect.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.smarthome.apps.jrm.jrm_connect.R;

public class DeleteConfirmationActivity extends AppCompatActivity {

    Button del,can;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_delete_confirmation);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.2));

        del = (Button) findViewById(R.id.delete);
        can = (Button) findViewById(R.id.cancel);

        final Intent intent = new Intent();


        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = getIntent().getStringExtra("indexCode");
                intent.putExtra("indexcodereturned",s);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("indexcodereturned","canceled");
                setResult(RESULT_OK, intent);
                finish();
            }
        });



    }
}
