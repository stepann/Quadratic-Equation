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

    TextView tv_b_squared, tv_a, tv_c, tv_discriminantSign, tv_discriminant, tv_discriminant_sqrt, tv_discriminant_sqrt_char, tv_result_first_numerator, tv_result_second_numerator,
            tv_result_first_denominator, tv_result_second_denominator, tv_result_first, tv_result_second, tv_inform_message, tv_numerator_formula, tv_numerator_formula_complex;
    RelativeLayout rl_root_first, rl_root_second;
    View v_divider_first, v_divider_second, v_divider_formula;

    public Steps_fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_steps_fragment, container, false);


        tv_b_squared = (TextView) view.findViewById(R.id.tv_DicriminantSteps_B);
        tv_a = (TextView) view.findViewById(R.id.tv_DiscriminantSteps_valueA);
        tv_c = (TextView) view.findViewById(R.id.tv_DiscriminantSteps_valueC);
        tv_discriminantSign = (TextView) view.findViewById(R.id.tv_DiscriminantSteps_sign);
        tv_discriminant = (TextView) view.findViewById(R.id.tv_DiscriminantSteps_diskriminant);
        tv_discriminant_sqrt = (TextView) view.findViewById(R.id.tv_DiscriminantSteps_discriminant_sqrt);
        tv_discriminant_sqrt_char = (TextView) view.findViewById(R.id.tv_DiscriminantSteps_discriminant_sqrt_char);

        tv_result_first = (TextView) view.findViewById(R.id.tv_ResultFirst_result_first);
        tv_result_second = (TextView) view.findViewById(R.id.tv_ResultSecond_result_second);


        tv_inform_message = (TextView) view.findViewById(R.id.tv_DiscriminantSteps_INVISIBLE_message);

        tv_numerator_formula = (TextView) view.findViewById(R.id.tv_RootSteps_numerator_formula);
        tv_numerator_formula_complex = (TextView) view.findViewById(R.id.tv_RootSteps_numerator_complex_formula);

        tv_result_first_numerator = (TextView) view.findViewById(R.id.tv_ResultFirst_numerator);
        tv_result_second_numerator = (TextView) view.findViewById(R.id.tv_ResultSecond_numerator);

        tv_result_first_denominator = (TextView) view.findViewById(R.id.tv_ResultFirst_denominator_first);
        tv_result_second_denominator = (TextView) view.findViewById(R.id.tv_ResultSecond_denominator_second);


        rl_root_first = (RelativeLayout) view.findViewById(R.id.rl_result_first);
        rl_root_second = (RelativeLayout) view.findViewById(R.id.rl_result_second);

        v_divider_first = view.findViewById(R.id.v_ResultFirst_divider_first);
        v_divider_second = view.findViewById(R.id.v_ResultSecond_divider_second);
        v_divider_formula = view.findViewById(R.id.view2);


        updateText();
        return view;
    }


    private void updateText() {

        SolverActivity activity = (SolverActivity) getActivity();

        tv_b_squared.setText(activity.B_squared);
        tv_a.setText(activity.absoluteValueA);
        tv_c.setText(activity.str_C);
        tv_discriminantSign.setText(activity.signInDiscriminantFormula);
        tv_discriminant.setText(activity.str_discriminant);


        if (activity.value.contains("negative")) {

            tv_discriminant_sqrt_char.setVisibility(View.GONE);
            tv_discriminant_sqrt.setVisibility(View.GONE);
            tv_inform_message.setVisibility(View.VISIBLE);
            tv_inform_message.setText(R.string.complex_message);
            tv_numerator_formula.setVisibility(View.GONE);
            tv_numerator_formula_complex.setVisibility(View.VISIBLE);

            replace_complex_b_sign();
            replace_complex_a_sign();

            //print results
            tv_result_first.setText(activity.str_complex_root_first);
            tv_result_second.setText(activity.str_complex_root_second);

        }

        //discriminant is zero
        if (activity.value.contains("zero")) {

            tv_discriminant_sqrt_char.setVisibility(View.GONE);
            tv_discriminant_sqrt.setVisibility(View.GONE);
            tv_inform_message.setVisibility(View.VISIBLE);
            tv_inform_message.setText(R.string.disc_is_zero);
            rl_root_second.setVisibility(View.GONE);

            if (activity.dbl_b < 0) {
                tv_result_first_numerator.setText(activity.str_B.replace("-", "") + " + " + activity.str_discriminant_sqrt);
            } else
                tv_result_first_numerator.setText("-" + activity.str_B + " + " + activity.str_discriminant_sqrt);

            if (activity.dbl_a < 0) {
                tv_result_first_denominator.setText("-2" + getString(R.string.times_char) + activity.str_A.replace("-", ""));

            } else {
                tv_result_first_denominator.setText(getString(R.string.second_denominator) + activity.str_A);

            }
            //print result
            tv_result_first.setText(activity.str_root_first);

        }


        //discriminant is positive (D > 0)
        if (activity.value.contains("positive")) {
            tv_discriminant_sqrt.setText(activity.str_discriminant_sqrt);

            replace_b_sign();
            replace_a_sign();

            //print results
            tv_result_first.setText(activity.str_root_first);
            tv_result_second.setText(activity.str_root_second);
        }
    }


    //changing sign if the B is minus
    private void replace_complex_b_sign() {
        SolverActivity activity = (SolverActivity) getActivity();
        if (activity.dbl_B < 0) {
            tv_result_first_numerator.setText(activity.str_B.replace("-", " ") + " + " + "|-" + activity.str_complex_discriminant + "|" + "i");
            tv_result_second_numerator.setText(activity.str_B.replace("-", " ") + " - " + "|-" + activity.str_complex_discriminant + "|" + "i");
        } else {
            tv_result_first_numerator.setText("-" + activity.str_B + " + " + "|-" + activity.str_complex_discriminant + "|" + "i");
            tv_result_second_numerator.setText("-" + activity.str_B + " - " + "|-" + activity.str_complex_discriminant + "|" + "i");
        }
    }

    private void replace_b_sign() {
        SolverActivity activity = (SolverActivity) getActivity();
        if (activity.dbl_B < 0) {
            tv_result_first_numerator.setText(activity.str_B.replace("-", "") + " + " + activity.str_discriminant_sqrt);
            tv_result_second_numerator.setText(activity.str_B.replace("-", "") + " - " + activity.str_discriminant_sqrt);
        } else {
            tv_result_first_numerator.setText("-" + activity.str_B + " + " + activity.str_discriminant_sqrt);
            tv_result_second_numerator.setText("-" + activity.str_B + " - " + activity.str_discriminant_sqrt);
            tv_result_first_denominator.setText(getString(R.string.second_denominator) + activity.str_A);
            tv_result_second_denominator.setText(getString(R.string.second_denominator) + activity.str_A);
        }
    }

    //changing sign if the A is minus
    private void replace_complex_a_sign() {
        SolverActivity activity = (SolverActivity) getActivity();
        if (activity.dbl_A < 0) {
            tv_result_first_denominator.setText("-2" + getString(R.string.times_char) + activity.str_A.replace("-", ""));
            tv_result_second_denominator.setText("-2" + getString(R.string.times_char) + activity.str_A.replace("-", ""));

        } else {
            tv_result_first_denominator.setText(getString(R.string.second_denominator) + activity.str_A);
            tv_result_second_denominator.setText(getString(R.string.second_denominator) + activity.str_A);
        }
    }


    private void replace_a_sign() {
        SolverActivity activity = (SolverActivity) getActivity();
        if (activity.dbl_A < 0) {
            tv_result_first_denominator.setText("-2" + getString(R.string.times_char) + activity.str_A.replace("-", ""));
            tv_result_second_denominator.setText("-2" + getString(R.string.times_char) + activity.str_A.replace("-", ""));
        } else {
            tv_result_first_denominator.setText(getString(R.string.second_denominator) + activity.str_A);
            tv_result_second_denominator.setText(getString(R.string.second_denominator) + activity.str_A);
        }

    }
}




