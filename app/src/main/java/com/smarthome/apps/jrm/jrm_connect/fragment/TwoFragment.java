package com.smarthome.apps.jrm.jrm_connect.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smarthome.apps.jrm.jrm_connect.R;
import com.smarthome.apps.jrm.jrm_connect.activity.MainActivity;


public class TwoFragment extends Fragment {

    TextView ins;
    MainActivity m;

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        ins = (TextView) view.findViewById(R.id.ins);
        ins.setTextColor(getResources().getColor(R.color.whiteColor));

        m = new MainActivity();

        return view;
    }

    public int send(String s)
    {
        return m.sendData(s);
    }

}
