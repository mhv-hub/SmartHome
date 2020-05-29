package com.smarthome.apps.jrm.jrm_connect.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.smarthome.apps.jrm.jrm_connect.R;

public class SetTimerActivity extends AppCompatActivity {

    Button submit;
    EditText hours,mins;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_set_timer);

        submit = (Button) findViewById(R.id.submit);
        submit.setTextColor(getResources().getColor(R.color.green));
        hours = (EditText) findViewById(R.id.hours);
        mins = (EditText) findViewById(R.id.mins);
        textView = (TextView) findViewById(R.id.texttop);

        hours.setTextColor(getResources().getColor(R.color.blacklight));
        mins.setTextColor(getResources().getColor(R.color.blacklight));

        textView.setTextColor(getResources().getColor(R.color.whitelight));

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.35));

        final String index = getIntent().getStringExtra("actionCode");
        int switchno = Integer.parseInt(index)+1;
        final String switchStatus = getIntent().getStringExtra("switchStatus");

        textView.setText("SET TIMER FOR SWITCH "+switchno);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hr="",min="";

                hr = hours.getText().toString();
                min = mins.getText().toString();

                if(hr.length() == 0){
                    hr = "blank";
                }
                if(min.length() == 0){
                    min = "blank";
                }

                if(hr == "blank" && min == "blank"){
                    Toast.makeText(SetTimerActivity.this, "TIME CAN'T BE BLANK", Toast.LENGTH_SHORT).show();
                }
                else {

                    if(hr == "blank"){
                        hr = "0";
                    }
                    if(min == "blank"){
                        min = "0";
                    }

                    for (int j = 0; j < hr.length(); j++) {
                        if (!Character.isDigit(hr.charAt(j))) {
                            Toast.makeText(SetTimerActivity.this, "Enter a valid time", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    for (int j = 0; j < min.length(); j++) {
                        if (!Character.isDigit(min.charAt(j))) {
                            Toast.makeText(SetTimerActivity.this, "Enter a valid time", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    int h, m;
                    h = Integer.parseInt(hr);
                    m = Integer.parseInt(min);

                    if (h > 20 || h < 0) {
                        Toast.makeText(SetTimerActivity.this, "Unsupported Time", Toast.LENGTH_SHORT).show();
                    }

                    if (m > 59) {
                        while (true) {
                            if (m > 59) {
                                m = m - 60;
                                h++;
                            }
                        }
                    }
                    int time = h * 60 + m;
                    Intent intent = new Intent();
                    intent.putExtra("switchIndex", index);
                    intent.putExtra("timeInMinutes", "" + time);
                    intent.putExtra("switchStatus", switchStatus);
                    setResult(RESULT_OK,intent);
                    if(h == 0) {
                        Toast.makeText(SetTimerActivity.this, "TIMER SET FOR "+min+"", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(SetTimerActivity.this, "", Toast.LENGTH_SHORT).show();
                    }

                    finish();
                }


            }
        });
    }

    @Override
    protected void onDestroy() {

        Intent intent = new Intent();
        setResult(RESULT_CANCELED,intent);
        super.onDestroy();
    }
}
