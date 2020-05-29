package com.smarthome.apps.jrm.jrm_connect.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smarthome.apps.jrm.jrm_connect.R;

public class EntryClass extends AppCompatActivity{

    SQLiteDatabase db;
    Button submit;
    EditText mnp,mxp,mnpwr,mxpwr,m1pwr,m2pwr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.class_entry);

        db = openOrCreateDatabase("Unit_database", Context.MODE_PRIVATE,null);
        db.execSQL("create table if not exists data(minpump varchar(5),maxpump varchar(5),minpower varchar(10),maxpower varchar(10),mid1power varchar(10),mid2power varchar(10))");

        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.6));

        mnp = (EditText) findViewById(R.id.pumpmin);
        mxp = (EditText) findViewById(R.id.pumpmax);
        mnpwr = (EditText) findViewById(R.id.powermin);
        mxpwr = (EditText) findViewById(R.id.powermax);
        m1pwr = (EditText) findViewById(R.id.powermidone);
        m2pwr = (EditText) findViewById(R.id.powermidtwo);

        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("INSERT INTO data VALUES('"+mnp.getText().toString()+"','"+mxp.getText().toString()+"','"+mnpwr.getText().toString()+"','"+mxpwr.getText().toString()+"','"+m1pwr.getText().toString()+"','"+m2pwr.getText().toString()+"');");
                Toast.makeText(EntryClass.this, "DETAILS SUBMITTED SUCCESSFUL", Toast.LENGTH_LONG).show();
            }
        });


    }
}
