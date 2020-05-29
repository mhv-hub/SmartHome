package com.smarthome.apps.jrm.jrm_connect.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.smarthome.apps.jrm.jrm_connect.R;
import com.smarthome.apps.jrm.jrm_connect.activity.MainActivity;
import com.smarthome.apps.jrm.jrm_connect.activity.SetTimerActivity;


public class OneFragment extends Fragment {


    Button l1,l2,l3,f1,f2,f3,btn7,btn8,otp,sub;
    String battery;
    EditText batvalue;
    int counter = 0;
    TextView ins,tl1,tl2,tl3,tf1,tf2,tsd,tf3,tbtn7,tbtn8;
    MainActivity m;
    int st = 0;
    public OneFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_one, container, false);

        m = new MainActivity();

        batvalue = (EditText) view.findViewById(R.id.batvalue);

        l1 = (Button) view.findViewById(R.id.l1);
        l2 = (Button) view.findViewById(R.id.l2);
        l3 = (Button) view.findViewById(R.id.l3);
        f1 = (Button) view.findViewById(R.id.f1);
        f2 = (Button) view.findViewById(R.id.f2);
        f3 = (Button) view.findViewById(R.id.f3);
        sub = (Button) view.findViewById(R.id.sub);
        btn7 = (Button) view.findViewById(R.id.btn7);
        btn8 = (Button) view.findViewById(R.id.btn8);
        otp = (Button) view.findViewById(R.id.sd);

        ins = (TextView) view.findViewById(R.id.ins);
        tl1 = (TextView) view.findViewById(R.id.tl1);
        tl2 = (TextView) view.findViewById(R.id.tl2);
        tl3 = (TextView) view.findViewById(R.id.tl3);
        tf1 = (TextView) view.findViewById(R.id.tf1);
        tf2 = (TextView) view.findViewById(R.id.tf2);
        tsd = (TextView) view.findViewById(R.id.tsd);
        tbtn7 = (TextView) view.findViewById(R.id.tbtn7);
        tbtn8 = (TextView) view.findViewById(R.id.tbtn8);
        tf3 = (TextView) view.findViewById(R.id.tf3);

        ins.setTextColor(getResources().getColor(R.color.whiteColor));
        tl1.setTextColor(getResources().getColor(R.color.msg));
        tl2.setTextColor(getResources().getColor(R.color.msg));
        tl3.setTextColor(getResources().getColor(R.color.msg));
        tf1.setTextColor(getResources().getColor(R.color.msg));
        tf2.setTextColor(getResources().getColor(R.color.msg));
        tsd.setTextColor(getResources().getColor(R.color.msg));
        tbtn7.setTextColor(getResources().getColor(R.color.msg));
        tbtn8.setTextColor(getResources().getColor(R.color.msg));
        tf3.setTextColor(getResources().getColor(R.color.msg));



        l1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(getActivity(), SetTimerActivity.class);
                i.putExtra("actionCode","00");
                if(f1.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnoff).getConstantState())){
                    i.putExtra("switchStatus","0");
                }
                else{
                    i.putExtra("switchStatus","1");
                }
                getActivity().startActivityForResult(i,11);
                return true;
            }
        });

        l2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(getActivity(), SetTimerActivity.class);
                i.putExtra("actionCode","01");
                if(f1.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnoff).getConstantState())){
                    i.putExtra("switchStatus","0");
                }
                else{
                    i.putExtra("switchStatus","1");
                }
                getActivity().startActivityForResult(i,11);
                return true;
            }
        });

        l3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(getActivity(), SetTimerActivity.class);
                i.putExtra("actionCode","02");
                getActivity().startActivityForResult(i,11);
                return true;
            }
        });

        f1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(getActivity(), SetTimerActivity.class);
                i.putExtra("actionCode","03");
                if(f1.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnoff).getConstantState())){
                    i.putExtra("switchStatus","0");
                }
                else{
                    i.putExtra("switchStatus","1");
                }
                getActivity().startActivityForResult(i,11);
                return true;
            }
        });

        f2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(getActivity(), SetTimerActivity.class);
                i.putExtra("actionCode","04");
                if(f1.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnoff).getConstantState())){
                    i.putExtra("switchStatus","0");
                }
                else{
                    i.putExtra("switchStatus","1");
                }
                getActivity().startActivityForResult(i,11);
                return true;
            }
        });

        f3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(getActivity(), SetTimerActivity.class);
                i.putExtra("actionCode","05");
                if(f1.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnoff).getConstantState())){
                    i.putExtra("switchStatus","0");
                }
                else{
                    i.putExtra("switchStatus","1");
                }
                getActivity().startActivityForResult(i,11);
                return true;
            }
        });

        btn7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(getActivity(), SetTimerActivity.class);
                i.putExtra("actionCode","06");
                if(f1.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnoff).getConstantState())){
                    i.putExtra("switchStatus","0");
                }
                else{
                    i.putExtra("switchStatus","1");
                }
                getActivity().startActivityForResult(i,11);
                return true;
            }
        });

        btn8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(getActivity(), SetTimerActivity.class);
                i.putExtra("actionCode","07");
                if(f1.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnoff).getConstantState())){
                    i.putExtra("switchStatus","0");
                }
                else{
                    i.putExtra("switchStatus","1");
                }
                getActivity().startActivityForResult(i,11);
                return true;
            }
        });



        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter == 7)
                {
                    batvalue.setVisibility(View.VISIBLE);
                    sub.setVisibility(View.VISIBLE);
                    counter = 0;
                }
                counter++;
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                battery = batvalue.getText().toString();
                ((MainActivity)getActivity()).batLevel(Integer.parseInt(battery));
                batvalue.setVisibility(View.GONE);
                sub.setVisibility(View.GONE);
            }
        });


        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).toggleotp(st);
                    st = (st+1)%2;
                if(st == 1) {
                    Toast.makeText(getContext(), "SMS commands OFF", Toast.LENGTH_SHORT).show();
                    otp.setBackgroundResource(R.drawable.off);
                }
                else {
                    Toast.makeText(getContext(), "SMS Commands ON", Toast.LENGTH_SHORT).show();
                    otp.setBackgroundResource(R.drawable.offclick);
                }
            }
        });




        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(l1.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnoff).getConstantState())) {
                    if((((MainActivity)getActivity()).sendData("A"))==1)
                        l1.setBackgroundResource(R.drawable.btnon);
                    else
                        Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
                else {
                    if((((MainActivity)getActivity()).sendData("a"))==1)
                        l1.setBackgroundResource(R.drawable.btnoff);
                    else
                        Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
            }
        });


        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(l2.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnoff).getConstantState())) {
                    if((((MainActivity)getActivity()).sendData("B"))==1)
                        l2.setBackgroundResource(R.drawable.btnon);
                    else
                        Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
                else {
                    if((((MainActivity)getActivity()).sendData("b"))==1)
                        l2.setBackgroundResource(R.drawable.btnoff);
                    else
                        Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
            }
        });


        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(l3.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnoff).getConstantState())) {
                    if((((MainActivity)getActivity()).sendData("C"))==1)
                        l3.setBackgroundResource(R.drawable.btnon);
                    else
                        Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
                else {
                    if((((MainActivity)getActivity()).sendData("c"))==1)
                        l3.setBackgroundResource(R.drawable.btnoff);
                    else
                        Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
            }
        });


        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(f1.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnoff).getConstantState())) {
                    if((((MainActivity)getActivity()).sendData("D"))==1)
                        f1.setBackgroundResource(R.drawable.btnon);
                    else
                        Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
                else {
                    if((((MainActivity)getActivity()).sendData("d"))==1)
                        f1.setBackgroundResource(R.drawable.btnoff);
                    else
                        Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
            }
        });


        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (f2.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnoff).getConstantState())){
                    if((((MainActivity)getActivity()).sendData("E"))==1)
                        f2.setBackgroundResource(R.drawable.btnon);
                    else
                        Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
                else if(f2.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnon).getConstantState())){
                    if((((MainActivity)getActivity()).sendData("e"))==1)
                        f2.setBackgroundResource(R.drawable.btnoff);
                    else
                        Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
            }
        });

        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f3.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnoff).getConstantState())){
                    if((((MainActivity)getActivity()).sendData("F"))==1)
                        f3.setBackgroundResource(R.drawable.btnon);
                    else
                        Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
                else if(f3.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnon).getConstantState())){
                    if((((MainActivity)getActivity()).sendData("f"))==1)
                        f3.setBackgroundResource(R.drawable.btnoff);
                    else
                        Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn7.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnoff).getConstantState())){
                    if((((MainActivity)getActivity()).sendData("G"))==1)
                        btn7.setBackgroundResource(R.drawable.btnon);
                    else
                        Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
                else if(btn7.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnon).getConstantState())){
                    if((((MainActivity)getActivity()).sendData("g"))==1)
                        btn7.setBackgroundResource(R.drawable.btnoff);
                    else
                        Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn8.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnoff).getConstantState())){
                    if((((MainActivity)getActivity()).sendData("H"))==1)
                        btn8.setBackgroundResource(R.drawable.btnon);
                    else
                        Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
                else if(btn8.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.btnon).getConstantState())){
                    if((((MainActivity)getActivity()).sendData("h"))==1)
                        btn8.setBackgroundResource(R.drawable.btnoff);
                    else
                        Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
                }
            }
        });

     /*   btn7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        btn7.setBackgroundResource(R.drawable.btnon);
                        ((MainActivity)getActivity()).sendData("F");
                        return true;
                    case MotionEvent.ACTION_UP:
                        btn7.setBackgroundResource(R.drawable.btnoff);
                        ((MainActivity)getActivity()).sendData("f");
                        return true;
                    case MotionEvent.ACTION_CANCEL:
                        return true;

                }
                return false;
            }
        });

        btn8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        btn8.setBackgroundResource(R.drawable.btnon);
                        ((MainActivity)getActivity()).sendData("G");
                        return true;
                    case MotionEvent.ACTION_UP:
                        btn8.setBackgroundResource(R.drawable.btnoff);
                        ((MainActivity)getActivity()).sendData("g");
                        return true;
                }
                return false;
            }
        }); */


        return view;
    }



    public void recieve(String s)
    {

        //Toast.makeText(getActivity(), "From frag : "+s, Toast.LENGTH_SHORT).show();

        if (s.charAt(0) == '0') {
            l1.setBackgroundResource(R.drawable.btnon);
        }
        else if(s.charAt(0)=='1'){
            l1.setBackgroundResource(R.drawable.btnoff);
        }

        if (s.charAt(1) == '0') {
            l2.setBackgroundResource(R.drawable.btnon);
        }
        else if(s.charAt(1)=='1'){
            l2.setBackgroundResource(R.drawable.btnoff);
        }

        if (s.charAt(2) == '0') {
            l3.setBackgroundResource(R.drawable.btnon);
        }
        else if(s.charAt(2)=='1'){
            l3.setBackgroundResource(R.drawable.btnoff);
        }

        if (s.charAt(3) == '0') {
            f1.setBackgroundResource(R.drawable.btnon);
        }
        else if(s.charAt(3)=='1'){
            f1.setBackgroundResource(R.drawable.btnoff);
        }

        if (s.charAt(4) == '0') {
            f2.setBackgroundResource(R.drawable.btnon);
        }
        else if(s.charAt(4)=='1'){
            f2.setBackgroundResource(R.drawable.btnoff);
        }

        if (s.charAt(5) == '0') {
            f3.setBackgroundResource(R.drawable.btnon);
        }
        else if(s.charAt(5)=='1'){
            f3.setBackgroundResource(R.drawable.btnoff);
        }

        if (s.charAt(6) == '0') {
            btn7.setBackgroundResource(R.drawable.btnon);
        }
        else if(s.charAt(6)=='1'){
            btn7.setBackgroundResource(R.drawable.btnoff);
        }

        if (s.charAt(7) == '0') {
            btn8.setBackgroundResource(R.drawable.btnon);
        }
        else if(s.charAt(7)=='1'){
            btn8.setBackgroundResource(R.drawable.btnoff);
        }
    }

}
