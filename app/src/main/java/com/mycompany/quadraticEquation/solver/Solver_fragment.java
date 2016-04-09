package com.mycompany.quadraticEquation.solver;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Solver_fragment extends Fragment {

    public TextView tv_A, tv_B, tv_C, tv_SymbolB, tv_symbolC, tv_discriminant,
                    tv_root_first, tv_root_second, tv_x_first, tv_x_first_sub;
    public TextView tv_x_second, tv_x_second_sub;
    public TextView tv_Bx;
    public TextView tv_equal_first, tv_equal_second;

    public Solver_fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_solver_fragment, container, false);


        //define IDs and
        tv_A = (TextView)v.findViewById(R.id.tv_EquationSolverFragment_A);
        tv_B = (TextView)v.findViewById(R.id.tv_EquationSolverFragment_B);
        tv_C = (TextView)v.findViewById(R.id.tv_EquationSolverFragment_Cx);
        tv_SymbolB = (TextView)v.findViewById(R.id.tv_EquationSolverFragment_symbolB);
        tv_symbolC = (TextView)v.findViewById(R.id.tv_EquationSolverFragment_symbolC);
        tv_discriminant = (TextView)v.findViewById(R.id.tv_SolverFragment_discriminantValue);
        tv_root_first = (TextView)v.findViewById(R.id.tv_SolverFragment_root_first);
        tv_Bx = (TextView)v.findViewById(R.id.tv_EquationSolverFragment_Bx);
        tv_root_second = (TextView)v.findViewById(R.id.tv_SolverFragment_root_second);

        tv_x_first = (TextView)v.findViewById(R.id.tv_SolverFragment_x_first);
        tv_x_first_sub = (TextView)v.findViewById(R.id.tv_SolverFragment_x_sub_first);
        tv_equal_first = (TextView)v.findViewById(R.id.tv_SolverFragment_equal_first);

        tv_x_second = (TextView)v.findViewById(R.id.tv_SolverFragment_x_second);
        tv_x_second_sub = (TextView)v.findViewById(R.id.tv_SolverFragment_x_sub_second);
        tv_equal_second = (TextView)v.findViewById(R.id.tv_SolverFragment_equal_second);

        updateTextViews();
        dicriminantTextView();

    return v;
    }

    private void updateTextViews() {
        SolverActivity activity = (SolverActivity)getActivity();
        switch (activity.String_A) {
            case ("1"):
                tv_A.setVisibility(View.GONE);
                break;
            default:
                tv_A.setText(activity.String_A);
        }
        switch (activity.String_B) {
            case ("1"):
                tv_B.setVisibility(View.GONE);
                break;
            case ("0"):
                tv_B.setVisibility(View.GONE);
                tv_Bx.setText(" ");
                tv_SymbolB.setVisibility(View.GONE);
            default:
                tv_B.setText(activity.String_B);
        }
        switch (activity.String_C) {
            case ("0"):
                tv_symbolC.setVisibility(View.GONE);
                tv_C.setVisibility(View.GONE);
                break;
            default:
                tv_C.setText(activity.String_C + " ");
        }


        tv_SymbolB.setText(activity.symbolB);
        tv_symbolC.setText(activity.symbolC);
        tv_discriminant.setText(activity.String_discriminant);

    }

    private void dicriminantTextView() {
        SolverActivity activity = (SolverActivity)getActivity();

        switch (activity.value) {
            case ("negative"):
                tv_x_first_sub.setText(Html.fromHtml("<sub>1</sub>"));
                tv_x_second_sub.setText(Html.fromHtml("<sub>2<sub>"));
                tv_root_first.setText(activity.COMPLEX_ROOT_FIRST);
                tv_root_second.setText(activity.COMPLEX_ROOT_SECOND);
                break;

            case ("positive"):
                tv_x_first_sub.setText(Html.fromHtml("<sub>1</sub>"));
                tv_x_second_sub.setText(Html.fromHtml("<sub>2<sub>"));
                tv_root_first.setText(activity.String_root_first);
                tv_root_second.setText(activity.String_root_second);
                break;

            case("zero"):
                tv_x_second.setText("");
                tv_x_second_sub.setText("");
                tv_equal_second.setText("");
                tv_root_second.setText("");
                tv_x_first_sub.setText(Html.fromHtml("<sub>1</sub>"));
                tv_root_first.setText(activity.String_root_first);

        }
    }

}



