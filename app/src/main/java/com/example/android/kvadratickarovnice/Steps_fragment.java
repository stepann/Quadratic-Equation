package com.example.android.kvadratickarovnice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Steps_fragment extends Fragment {

    TextView b_2, a, c, sign_disk, diskriminant, sqrt_diskriminant, sqrt_text, root_text, vrsek, vrsek2, spodek, spodek2, vysledek_x1,vysledek_x2;
    RelativeLayout x1, x2;
    View cara, cara2;

    public Steps_fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_steps_fragment, container, false);

        b_2 = (TextView) v.findViewById(R.id.b_2);
        a = (TextView) v.findViewById(R.id.a);
        c = (TextView) v.findViewById(R.id.c);
        sign_disk = (TextView) v.findViewById(R.id.sign_disk);
        diskriminant = (TextView) v.findViewById(R.id.diskriminant);
        sqrt_diskriminant = (TextView) v.findViewById(R.id.sqrt_disk);
        sqrt_text = (TextView) v.findViewById(R.id.sqrt_disk_text);
        root_text = (TextView) v.findViewById(R.id.root_text);
        vysledek_x1 = (TextView)v.findViewById(R.id.vysledek_x1);
        vysledek_x2 = (TextView)v.findViewById(R.id.vysledek_x2);

        vrsek = (TextView) v.findViewById(R.id.vrsek);
        vrsek2 = (TextView)v.findViewById(R.id.vrsek2);

        spodek = (TextView) v.findViewById(R.id.spodek);
        spodek2 = (TextView)v.findViewById(R.id.spodek2);

        x1 = (RelativeLayout) v.findViewById(R.id.x1);
        x2 = (RelativeLayout)v.findViewById(R.id.x2);

        cara = v.findViewById(R.id.cara);
        cara2 = v.findViewById(R.id.cara2);

        updateText();
        return v;


    }

    private void updateText() {

        Solver_activity activity = (Solver_activity) getActivity();

        b_2.setText(activity.B_2);
        a.setText(activity.AbsA);
        c.setText(activity.C);
        sign_disk.setText(activity.disk);
        //Log.e("disk", "disk " + activity.disk);
        diskriminant.setText(activity.D);
        vysledek_x1.setText(activity.string_koren1);
        vysledek_x2.setText(activity.string_koren2);

        if (activity.D.contains("-")) {
            sqrt_diskriminant.setText(getString(R.string.not_poss));
            //Log.e("String1", "nelze");

        } else sqrt_diskriminant.setText(activity.String_odm_d);


        if (activity.D.contains("-")) {
            //D is negative, show "not possible" message
            root_text.setVisibility(View.VISIBLE);
            x1.setVisibility(View.GONE);
            x2.setVisibility(View.GONE);
        } else {
            if (activity.B.contains("-")) {
                //B_FORM = activity.B.replace("-", "");
                vrsek.setText(activity.B.replace("-", " ") + " + " + activity.String_odm_d);
                vrsek2.setText(activity.B.replace("-", " ") + " - " + activity.String_odm_d);
            } else {
                vrsek.setText("-" + activity.B + " + " + activity.String_odm_d);
                vrsek2.setText("-" + activity.B + " - " + activity.String_odm_d);
                spodek.setText(getString(R.string.spodek2) + activity.A);
                spodek2.setText(getString(R.string.spodek2) + activity.A);
            }
            if (activity.A.contains("-")) {
                spodek.setText("-2" + getString(R.string.krat_char) + activity.A.replace("-", " "));
                spodek2.setText("-2" + getString(R.string.krat_char) + activity.A.replace("-", " "));
            } else {
                spodek.setText(getString(R.string.spodek2) + activity.A);
                spodek2.setText(getString(R.string.spodek2) + activity.A);
            }

            if (activity.String_odm_d.length() > 3) {
                cara.getLayoutParams().width = 500;
                cara2.getLayoutParams().width = 500;

            }
            if (activity.String_odm_d.length() > 5) {
                cara.getLayoutParams().width = 600;
                cara2.getLayoutParams().width = 600;

            }


        }

    }
}
