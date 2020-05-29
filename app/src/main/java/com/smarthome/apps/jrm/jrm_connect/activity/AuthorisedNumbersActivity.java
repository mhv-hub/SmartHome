package com.smarthome.apps.jrm.jrm_connect.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smarthome.apps.jrm.jrm_connect.R;

import java.util.ArrayList;

public class AuthorisedNumbersActivity extends AppCompatActivity {

    SQLiteDatabase db;
    boolean firstUse;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    int i = 0;
    RelativeLayout list,nolist;
    EditText newnum;
    Button addNew;
    String number;
    TextView asn,asn2;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_authorised_numbers);

        listView = (ListView) findViewById(R.id.num_list);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent i = new Intent(AuthorisedNumbersActivity.this,DeleteConfirmationActivity.class);
                        int index = position;
                        i.putExtra("indexCode",""+index);
                        startActivityForResult(i,18);
                    }
                }
        );

        list = (RelativeLayout) findViewById(R.id.list);
        nolist = (RelativeLayout) findViewById(R.id.nolist);

        newnum = (EditText) findViewById(R.id.newnum);
        addNew = (Button) findViewById(R.id.newNumber);

        addNew.setTextColor(getResources().getColor(R.color.whitelight));

        asn = (TextView) findViewById(R.id.asn);
        asn2 = (TextView) findViewById(R.id.asn2);


        asn.setTextColor(getResources().getColor(R.color.whitelight));
        asn2.setTextColor(getResources().getColor(R.color.whitelight));

        checkList();

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = newnum.getText().toString();
                if(number.equals(null)){
                    Toast.makeText(AuthorisedNumbersActivity.this, "Number can't be empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(number.length() < 10){
                        Toast.makeText(AuthorisedNumbersActivity.this, "Enter a valid number", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        db = openOrCreateDatabase("unit_database",Context.MODE_PRIVATE,null);
                        db.execSQL("create table if not exists numbers(nums number(15))");

                        if(checkNumber(number) == true) {

                            db.execSQL("insert into numbers values('" + number + "')");
                            db.close();
                            Toast.makeText(AuthorisedNumbersActivity.this, "Number added", Toast.LENGTH_SHORT).show();
                            newnum.setText("");
                            checkList();
                        }
                        else{
                            newnum.setText("");
                        }
                    }
                }
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 18) {
            if(resultCode == RESULT_OK){
                int index;
                String s = data.getStringExtra("indexcodereturned");
                if(s.equals("canceled")){
                    return;
                }
                else {
                    index = Integer.parseInt(s);
                    String number = arrayList.get(index);
                    arrayList.remove(index);
                    adapter = new ArrayAdapter<String>(this,R.layout.list_layout,arrayList);
                    listView.setAdapter(adapter);
                    db = openOrCreateDatabase("unit_database", Context.MODE_PRIVATE, null);
                    db.execSQL("delete from numbers where nums = '" + number + "'");
                    db.close();
                    Toast.makeText(AuthorisedNumbersActivity.this, "Number Removed", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }




    public boolean checkNumber(String number){
        int length = number.length();
        if(length>10){
            Toast.makeText(AuthorisedNumbersActivity.this, "Enter 10 digit mobile number", Toast.LENGTH_SHORT).show();
            return false;
        }
        for(int i=0;i<length;i++){
            if(!Character.isDigit(number.charAt(i))){
                Toast.makeText(AuthorisedNumbersActivity.this, "Invalid number", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        db = openOrCreateDatabase("unit_database",Context.MODE_PRIVATE,null);
        Cursor c = db.rawQuery("select * from numbers",null);
        c.moveToFirst();
        try{
            String s = c.getString(0);
            Cursor c1 = db.rawQuery("select * from numbers",null);
            while(c1.moveToNext()){
                if(number.equals(c1.getString(0))){
                    Toast.makeText(AuthorisedNumbersActivity.this, "Number Already registered", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }catch(Exception e){
            return true;
        }
        return true;
    }




    void checkList(){
            db = openOrCreateDatabase("unit_database", Context.MODE_PRIVATE,null);
            db.execSQL("create table if not exists numbers(nums number(15))");
            Cursor c = db.rawQuery("select * from numbers",null);
            c.moveToNext();


            try{
                String s = c.getString(0);
                firstUse = false;
            }catch (Exception e){
                firstUse = true;
                db.close();
            }

            if(firstUse == false) {
                Cursor c1 = db.rawQuery("select * from numbers",null);
                Cursor c3 = db.rawQuery("select count(nums) from numbers",null);
                c3.moveToFirst();
                int x = Integer.parseInt(c3.getString(0));


                arrayList.clear();
                for(int j=1;j<=x;j++){
                    c1.moveToNext();
                    arrayList.add(c1.getString(0));
                }


                db.close();

                adapter = new ArrayAdapter<String>(this,R.layout.list_layout,arrayList);
                nolist.setVisibility(View.GONE);
                list.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);
                listView.setAdapter(adapter);
            }
            else{
                    list.setVisibility(View.GONE);
                    nolist.setVisibility(View.VISIBLE);
            }
    }
}
