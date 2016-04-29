package com.mycompany.quadraticEquation.solver;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Steps_fragment extends Fragment {

    TextView tv_b_squared, tv_a, tv_c, tv_discriminantSign, tv_diskriminant, tv_discriminant_sqrt, tv_discriminant_sqrt_char, tv_result_first_numerator, tv_result_second_numerator,
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
        tv_diskriminant = (TextView) view.findViewById(R.id.tv_DiscriminantSteps_diskriminant);
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

        tv_b_squared.setText(activity.B_SQUARED);
        tv_a.setText(activity.AbsA);
        tv_c.setText(activity.String_C);
        if (activity.signInDiscriminantFormula != null) tv_discriminantSign.setText(activity.signInDiscriminantFormula);
        tv_diskriminant.setText(activity.String_discriminant);


        if (activity.value.contains("negative")) {

            tv_discriminant_sqrt_char.setVisibility(View.GONE);
            tv_discriminant_sqrt.setVisibility(View.GONE); //dismiss String_discriminant squared
            tv_inform_message.setVisibility(View.VISIBLE);
            tv_inform_message.setText(R.string.complex_message); //tv_inform_message which inform user that String_discriminant < 0
            tv_numerator_formula.setVisibility(View.GONE); //dismiss formula in real numbers
            tv_numerator_formula_complex.setVisibility(View.VISIBLE); //set formula in complex numbers

            replace_complex_b_sign();
            replace_complex_a_sign();
            complex_longerDivider();

            //print results
            tv_result_first.setText(activity.COMPLEX_ROOT_FIRST);
            tv_result_second.setText(activity.COMPLEX_ROOT_SECOND);

        }

        //String_discriminant = 0
        if (activity.value.contains("zero")) {

            tv_discriminant_sqrt_char.setVisibility(View.GONE);
            tv_discriminant_sqrt.setVisibility(View.GONE); //dismiss String_discriminant squared
            tv_inform_message.setVisibility(View.VISIBLE);
            tv_inform_message.setText(R.string.disc_is_zero); //tv_inform_message which inform user that String_discriminant < 0
            rl_root_second.setVisibility(View.GONE);

            if (activity.double_b < 0) {
                tv_result_first_numerator.setText(activity.String_B.replace("-", " ") + " + " + activity.String_discriminant_sqrt);
            } else
                tv_result_first_numerator.setText("-" + activity.String_B + " + " + activity.String_discriminant_sqrt);

            if (activity.double_a < 0) {
                tv_result_first_denominator.setText("-2" + getString(R.string.times_char) + activity.String_A.replace("-", " "));

            } else {
                tv_result_first_denominator.setText(getString(R.string.second_denominator) + activity.String_A);

            }
            //print result
            tv_result_first.setText(activity.String_root_first);

        }

        //String_discriminant is positive (D > 0)
        if (activity.value.contains("positive")) {
            tv_discriminant_sqrt.setText(activity.String_discriminant_sqrt);

            replace_b_sign();
            replace_a_sign();
            real_longerDivider();

            //print results
            tv_result_first.setText(activity.String_root_first);
            tv_result_second.setText(activity.String_root_second);
        }
    }

    //changing sign if the B is minus
    private void replace_complex_b_sign() {
        SolverActivity activity = (SolverActivity) getActivity();
        if (activity.double_b < 0) {
            tv_result_first_numerator.setText(activity.String_B.replace("-", " ") + " + " + "|-" + activity.COMPLEX_DISCRIMINANT + "|" + "i");
            tv_result_second_numerator.setText(activity.String_B.replace("-", " ") + " - " + "|-" + activity.COMPLEX_DISCRIMINANT + "|" + "i");
        } else {
            tv_result_first_numerator.setText("-" + activity.String_B + " + " + "|-" + activity.COMPLEX_DISCRIMINANT + "|" + "i");
            tv_result_second_numerator.setText("-" + activity.String_B + " - " + "|-" + activity.COMPLEX_DISCRIMINANT + "|" + "i");
        }
    }
    private void replace_b_sign() {
        SolverActivity activity = (SolverActivity) getActivity();
        if (activity.double_b < 0) {
            tv_result_first_numerator.setText(activity.String_B.replace("-", " ") + " + " + activity.String_discriminant_sqrt);
            tv_result_second_numerator.setText(activity.String_B.replace("-", " ") + " - " + activity.String_discriminant_sqrt);
        } else {
            tv_result_first_numerator.setText("-" + activity.String_B + " + " + activity.String_discriminant_sqrt);
            tv_result_second_numerator.setText("-" + activity.String_B + " - " + activity.String_discriminant_sqrt);
            tv_result_first_denominator.setText(getString(R.string.second_denominator) + activity.String_A);
            tv_result_second_denominator.setText(getString(R.string.second_denominator) + activity.String_A);
        }
    }

    //changing sign if te A s minus
    private void replace_complex_a_sign() {
        SolverActivity activity = (SolverActivity) getActivity();
        if (activity.double_a < 0) {
        tv_result_first_denominator.setText("-2" + getString(R.string.times_char) + activity.String_A.replace("-", " "));
        tv_result_second_denominator.setText("-2" + getString(R.string.times_char) + activity.String_A.replace("-", " "));

    } else {
        tv_result_first_denominator.setText(getString(R.string.second_denominator) + activity.String_A);
        tv_result_second_denominator.setText(getString(R.string.second_denominator) + activity.String_A);
    }

    }
    private void replace_a_sign() {
        SolverActivity activity = (SolverActivity) getActivity();
        if (activity.double_a < 0) {
            tv_result_first_denominator.setText("-2" + getString(R.string.times_char) + activity.String_A.replace("-", " "));
            tv_result_second_denominator.setText("-2" + getString(R.string.times_char) + activity.String_A.replace("-", " "));
        } else {
            tv_result_first_denominator.setText(getString(R.string.second_denominator) + activity.String_A);
            tv_result_second_denominator.setText(getString(R.string.second_denominator) + activity.String_A);
        }

    }

    //make divider longer depending on length
    private void real_longerDivider() {
        SolverActivity activity = (SolverActivity) getActivity();
        if (activity.String_discriminant_sqrt.length() >= 3 && activity.String_discriminant_sqrt.length() <= 5) {
            if(activity.double_b < 10) {
                v_divider_first.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width_small);
                v_divider_second.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width_small);
            }
            if(activity.double_b > 10) {
                v_divider_first.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width_medium);
                v_divider_second.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width_medium);
            }

        } else if (activity.String_discriminant_sqrt.length() >= 6) {
            //Log.i("TAG8", "real_longerDivider:tag " );
            v_divider_first.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width_high);
            v_divider_second.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width_high);
            if(activity.double_b > 1000) {
                //Log.i("TAG9", "real_longerDivider:tag2 " );
                v_divider_first.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width_max);
                v_divider_second.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width_max);
            }
        }
    }

    private void complex_longerDivider() {
        v_divider_formula.getLayoutParams().width = (int) getResources().getDimension(R.dimen.divider_complex);
        v_divider_first.getLayoutParams().width = (int) getResources().getDimension(R.dimen.divider_complex);
        v_divider_second.getLayoutParams().width = (int) getResources().getDimension(R.dimen.divider_complex);
        SolverActivity activity = (SolverActivity) getActivity();
        //Log.i("TAG1", "complex_longerDivider: " + activity.double_b);
        if (activity.COMPLEX_DISCRIMINANT.length() >= 3 && activity.COMPLEX_DISCRIMINANT.length() <= 5) {
            if(activity.double_b < 10) {
               // Log.i("TAG2", "complex_longerDivider: " + "case 1");
                v_divider_first.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width_small_complex);
                v_divider_second.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width_small_complex);
            }
            if(activity.double_b <= 100 && activity.double_b > 10) {
                //Log.i("TAG3", "complex_longerDivider: " + "case 2");
                v_divider_first.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width_medium_complex);
                v_divider_second.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width_medium_complex);
            }

        } else if (activity.COMPLEX_DISCRIMINANT.length() >= 6) {
            //Log.i("TAG4", "complex_longerDivider: " + "case 3");
            v_divider_first.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width_high_complex);
            v_divider_second.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width_high_complex);
            if(activity.double_b > 1000) {
                v_divider_first.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width_max_complex);
                v_divider_second.getLayoutParams().width = (int) getResources().getDimension(R.dimen.view_width_max_complex);
            }
        }

    }

}



