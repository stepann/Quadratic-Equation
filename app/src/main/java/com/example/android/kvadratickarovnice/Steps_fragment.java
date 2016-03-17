package com.example.android.kvadratickarovnice;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.TextDirectionHeuristic;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.awt.font.TextAttribute;


public class Steps_fragment extends Fragment {

    TextView b_2, a, c, sign_disk, diskriminant, sqrt_diskriminant, sqrt_text, root_text, vrsek;
    String B_FORM;

    public Steps_fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View v = inflater.inflate(R.layout.fragment_steps_fragment, container, false);

        b_2 = (TextView)v.findViewById(R.id.b_2);
        a = (TextView)v.findViewById(R.id.a);
        c = (TextView)v.findViewById(R.id.c);
        sign_disk = (TextView)v.findViewById(R.id.sign_disk);
        diskriminant = (TextView)v.findViewById(R.id.diskriminant);
        sqrt_diskriminant = (TextView)v.findViewById(R.id.sqrt_disk);
        sqrt_text = (TextView)v.findViewById(R.id.sqrt_disk_text);
        root_text = (TextView)v.findViewById(R.id.root_text);
        vrsek = (TextView)v.findViewById(R.id.vrsek);

        updateText();
        return v;



    }
    private void updateText() {

        Solver_activity activity = (Solver_activity)getActivity();

        b_2.setText(activity.B_2);
        a.setText(activity.AbsA);
        c.setText(activity.C);
        sign_disk.setText(activity.disk);
        //Log.e("disk", "disk " + activity.disk);
        diskriminant.setText(activity.D);

        //Log.e("String", "Diskriminant je : " + activity.D);
        if (activity.D.contains("-")) {
            sqrt_diskriminant.setText(getString(R.string.not_poss));
            //Log.e("String1", "nelze");

        }
        else sqrt_diskriminant.setText(activity.String_odm_d);


        if(activity.D.contains("-")) {
            root_text.setVisibility(View.VISIBLE);
        }
        if(activity.B.contains("-")) {
            //B_FORM = activity.B.replace("-", "");
            vrsek.setText(activity.B.replace("-", " ") + " + " + activity.String_odm_d);
        }
        else vrsek.setText("-" + activity.B + " + " + activity.String_odm_d);
    }


}
