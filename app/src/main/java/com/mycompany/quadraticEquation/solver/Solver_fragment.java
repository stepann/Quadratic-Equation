package com.mycompany.quadraticEquation.solver;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Solver_fragment extends Fragment {

    public TextView AtextView, BtextView, CtextView, SymbolTextViewB;
    public TextView SymbolTextViewC, DiskTextView;
    public TextView Koren_1, Koren_2;
    public TextView disk_text1, disk_text1_1;
    public TextView disk_text2, disk_text2_2;
    public TextView BX;
    public TextView equal1, equal2;

    public Solver_fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_solver_fragment, container, false);


        //define IDs and
        AtextView = (TextView)v.findViewById(R.id.Ax);
        BtextView = (TextView)v.findViewById(R.id.Bx);
        CtextView = (TextView)v.findViewById(R.id.Cx);
        SymbolTextViewB = (TextView)v.findViewById(R.id.symbolB);
        SymbolTextViewC = (TextView)v.findViewById(R.id.symbolC);
        DiskTextView = (TextView)v.findViewById(R.id.diskriminant_text);
        Koren_1 = (TextView)v.findViewById(R.id.koren_1);
        BX = (TextView)v.findViewById(R.id.Bx1);
        Koren_2 = (TextView)v.findViewById(R.id.koren_2);
        disk_text1 = (TextView)v.findViewById(R.id.text1);
        disk_text1_1 = (TextView)v.findViewById(R.id.text1_1);
        disk_text2 = (TextView)v.findViewById(R.id.text2);
        disk_text2_2 = (TextView)v.findViewById(R.id.text2_2);
        equal1 = (TextView)v.findViewById(R.id.equal1);
        equal2 = (TextView)v.findViewById(R.id.equal2);

        updateTextView();

    return v;
    }

    private void updateTextView() {
        Solver_activity activity = (Solver_activity)getActivity();
        switch (activity.A) {
            case ("1"):
                AtextView.setVisibility(View.GONE);
                break;
            default:
                AtextView.setText(activity.A);
        }
        switch (activity.B) {
            case ("1"):
                BtextView.setVisibility(View.GONE);
                break;
            case ("0"):
                BtextView.setVisibility(View.GONE);
                BX.setText(" ");
                SymbolTextViewB.setVisibility(View.GONE);
            default:
                BtextView.setText(activity.B);
        }
        switch (activity.C) {
            case ("0"):
                SymbolTextViewC.setVisibility(View.GONE);
                CtextView.setVisibility(View.GONE);
                break;
            default:
                CtextView.setText(activity.C + " ");
        }


        SymbolTextViewB.setText(activity.symbolB);
        SymbolTextViewC.setText(activity.symbolC);
        DiskTextView.setText(activity.D);

        switch (activity.value) {
            case ("negative"):
                Koren_1.setText(activity.complex_root_1);
                Koren_2.setText(activity.complex_root_2);
                break;

            case ("positive"):
                disk_text1_1.setText(Html.fromHtml("<sub>1</sub>"));
                disk_text2_2.setText(Html.fromHtml("<sub>2<sub>"));
                Koren_1.setText(activity.string_koren1);
                Koren_2.setText(activity.string_koren2);
                break;

            case("zero"):
                disk_text2.setText("");
                disk_text2_2.setText("");
                equal2.setText("");
                Koren_2.setText("");
                disk_text1_1.setText(Html.fromHtml("<sub>1</sub>"));
                Koren_1.setText(activity.string_koren1);

        }
    }

}



