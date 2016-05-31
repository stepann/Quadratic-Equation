package com.mycompany.quadraticEquation.solver;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

public class SolverActivity extends MainActivity {

    public String String_A, String_B, String_C, symbolB, symbolC, String_discriminant, String_root_first, String_root_second,
            value, B_SQUARED, AbsA, signInDiscriminantFormula, String_discriminant_sqrt, COMPLEX_ROOT_FIRST, COMPLEX_ROOT_SECOND, COMPLEX_DISCRIMINANT;
    public Double double_a, double_b;
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

    //method which gets variables from Main activity
    public void getVariables() {

        Bundle bundle = getIntent().getExtras();

        double_a = bundle.getDouble("double_a");
        double_b = bundle.getDouble("double_b");
        String_A = bundle.getString("kvadratickarovniceA");
        String_B = bundle.getString("kvadratickarovniceB");
        String_C = bundle.getString("kvadratickarovniceC");
        symbolB = bundle.getString("symbolB");
        symbolC = bundle.getString("symbolC");
        String_discriminant = bundle.getString("diskriminant");
        String_root_first = bundle.getString("double_root_first");
        String_root_second = bundle.getString("double_root_second");
        value = bundle.getString("val");
        B_SQUARED = bundle.getString("b_squared");
        AbsA = bundle.getString("AbsA");
        signInDiscriminantFormula = bundle.getString("signInDiscriminantFormula");
        String_discriminant_sqrt = bundle.getString("double_rooted_discriminant");
        COMPLEX_ROOT_FIRST = bundle.getString("complex_1");
        COMPLEX_ROOT_SECOND = bundle.getString("complex_2");
        COMPLEX_DISCRIMINANT = bundle.getString("COMPLEX_DISCRIMINANT");
    }

}


