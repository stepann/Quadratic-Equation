package com.mycompany.quadraticEquation.solver;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Steps_fragment extends Fragment {

    TextView b_2, a, c, sign_disk, diskriminant, sqrt_diskriminant, sqrt_text, vrsek, vrsek2,
            spodek, spodek2, vysledek_x1, vysledek_x2, message, vrsek_vzorec, vrsek_vzorec_complex;
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

        vysledek_x1 = (TextView) v.findViewById(R.id.vysledek_x1);
        vysledek_x2 = (TextView) v.findViewById(R.id.vysledek_x2);

        message = (TextView) v.findViewById(R.id.complex_message);

        vrsek_vzorec = (TextView) v.findViewById(R.id.vrsek_vzorec);
        vrsek_vzorec_complex = (TextView) v.findViewById(R.id.vrsek_vzorec_complex);
        vrsek = (TextView) v.findViewById(R.id.vrsek);
        vrsek2 = (TextView) v.findViewById(R.id.vrsek2);

        spodek = (TextView) v.findViewById(R.id.spodek);
        spodek2 = (TextView) v.findViewById(R.id.spodek2);

        x1 = (RelativeLayout) v.findViewById(R.id.x1);
        x2 = (RelativeLayout) v.findViewById(R.id.x2);

        cara = v.findViewById(R.id.cara);
        cara2 = v.findViewById(R.id.cara2);

        updateText();
        return v;


    }

    private void updateText() {

        Solver_activity activity = (Solver_activity) getActivity();
        Log.e("TAG", "updateText: " + activity.value);
        Log.e("TAG1", "updateText: " + activity.String_odm_d_complex);
        Log.e("TAG2", "updateText: " + activity.String_odm_d);

        b_2.setText(activity.B_2);
        a.setText(activity.AbsA);
        c.setText(activity.C);
        sign_disk.setText(activity.disk);
        diskriminant.setText(activity.D);


        if (activity.value.contains("negative")) {

            sqrt_text.setVisibility(View.GONE);
            sqrt_diskriminant.setVisibility(View.GONE); //dismiss D squared
            message.setVisibility(View.VISIBLE);
            message.setText(R.string.complex_message); //message which inform user that D < 0
            vrsek_vzorec.setVisibility(View.GONE); //dismiss formula in real numbers
            vrsek_vzorec_complex.setVisibility(View.VISIBLE); //set formula in complex numbers

            if (activity.b_this < 0) {
                vrsek.setText(activity.B.replace("-", " ") + " + " + "|-" + activity.String_odm_d_complex + "|" + "i");
                vrsek2.setText(activity.B.replace("-", " ") + " - " + "|-" + activity.String_odm_d_complex + "|" + "i");
            } else {
                vrsek.setText("-" + activity.B + " + " + "|-" + activity.String_odm_d_complex + "|" + "i");
                vrsek2.setText("-" + activity.B + " - " + "|-" + activity.String_odm_d_complex + "|" + "i");
            }
            if (activity.a_this < 0) {
                spodek.setText("-2" + getString(R.string.krat_char) + activity.A.replace("-", " "));
                spodek2.setText("-2" + getString(R.string.krat_char) + activity.A.replace("-", " "));

            } else {
                spodek.setText(getString(R.string.spodek2) + activity.A);
                spodek2.setText(getString(R.string.spodek2) + activity.A);
            }

            if (activity.String_odm_d_complex.length() >= 4) {
                cara.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width);
                cara2.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width);
            }

            vysledek_x1.setText(activity.complex_root_1);
            vysledek_x2.setText(activity.complex_root_2);

        }

        //D = 0
        if (activity.value.contains("zero")) {

            sqrt_text.setVisibility(View.GONE);
            sqrt_diskriminant.setVisibility(View.GONE); //dismiss D squared
            message.setVisibility(View.VISIBLE);
            message.setText(R.string.disk_zero); //message which inform user that D < 0
            x2.setVisibility(View.GONE);

            if (activity.b_this < 0) {
                vrsek.setText(activity.B.replace("-", " ") + " + " + activity.String_odm_d);
            } else vrsek.setText("-" + activity.B + " + " + activity.String_odm_d);

            if (activity.a_this < 0) {
                spodek.setText("-2" + getString(R.string.krat_char) + activity.A.replace("-", " "));

            } else {
                spodek.setText(getString(R.string.spodek2) + activity.A);

            }

            if (activity.String_odm_d.length() >= 4) {
                cara.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width);

            }

            vysledek_x1.setText(activity.string_koren1);

            }

        //D == +
        if (activity.value.contains("positive")) {
            sqrt_diskriminant.setText(activity.String_odm_d);

            if (activity.b_this < 0) {
                vrsek.setText(activity.B.replace("-", " ") + " + " + activity.String_odm_d);
                vrsek2.setText(activity.B.replace("-", " ") + " - " + activity.String_odm_d);
            } else {
                vrsek.setText("-" + activity.B + " + " + activity.String_odm_d);
                vrsek2.setText("-" + activity.B + " - " + activity.String_odm_d);
                spodek.setText(getString(R.string.spodek2) + activity.A);
                spodek2.setText(getString(R.string.spodek2) + activity.A);
            }
            if (activity.a_this < 0) {
                spodek.setText("-2" + getString(R.string.krat_char) + activity.A.replace("-", " "));
                spodek2.setText("-2" + getString(R.string.krat_char) + activity.A.replace("-", " "));
            } else {
                spodek.setText(getString(R.string.spodek2) + activity.A);
                spodek2.setText(getString(R.string.spodek2) + activity.A);
            }

            vysledek_x1.setText(activity.string_koren1);
            vysledek_x2.setText(activity.string_koren2);

            if (activity.String_odm_d.length() >= 4) {
                cara.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width);
                cara2.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width);
            }
        }

    }

}
