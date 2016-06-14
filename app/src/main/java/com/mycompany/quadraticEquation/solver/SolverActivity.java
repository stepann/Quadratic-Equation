package com.mycompany.quadraticEquation.solver;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

public class SolverActivity extends MainActivity {
    public String str_A, str_B, str_C, symbolB, symbolC, str_discriminant, str_root_first, str_root_second,
            value, B_squared, absoluteValueA, signInDiscriminantFormula, str_discriminant_sqrt, str_complex_root_first,
            str_complex_root_second, str_complex_discriminant;
    Double dbl_A, dbl_B;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(new Solver_fragment(), getString(R.string.Solution));
        viewPagerAdapter.addFragments(new Steps_fragment(), getString(R.string.Method));

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        getVariables();

    }

    //get values from MainActivity
    public void getVariables() {
        Bundle bundle = getIntent().getExtras();

        dbl_A = bundle.getDouble("double_a");
        dbl_B = bundle.getDouble("double_b");

        str_A = bundle.getString("value_A");
        absoluteValueA = bundle.getString("Abs_A");
        str_B = bundle.getString("value_B");
        B_squared = bundle.getString("b_squared");
        str_C = bundle.getString("value_C");

        symbolB = bundle.getString("symbol_B");
        symbolC = bundle.getString("symbol_C");

        str_discriminant = bundle.getString("discriminant");
        str_complex_discriminant = bundle.getString("complex_discriminant");
        str_root_first = bundle.getString("first_root");
        str_root_second = bundle.getString("second_root");
        str_complex_root_first = bundle.getString("complex_first_root");
        str_complex_root_second = bundle.getString("complex_second_root");

        signInDiscriminantFormula = bundle.getString("signInDiscriminantFormula");
        str_discriminant_sqrt = bundle.getString("sqrt_discriminant");
        value = bundle.getString("value");


    }

}


