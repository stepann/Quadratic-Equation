package com.mycompany.quadraticEquation.solver;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

public class Solver_activity extends MainActivity {

    public String A, B, C, symbolB, symbolC, D, string_koren1, string_koren2, value, B_2, AbsA, disk, String_odm_d;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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

        A = bundle.getString("kvadratickarovniceA");
        B = bundle.getString("kvadratickarovniceB");
        C = bundle.getString("kvadratickarovniceC");
        symbolB = bundle.getString("symbolB");
        symbolC = bundle.getString("symbolC");
        D = bundle.getString("diskriminant");
        string_koren1 = bundle.getString("x1");
        string_koren2 = bundle.getString("x2");
        value = bundle.getString("val");
        B_2 = bundle.getString("b_2");
        AbsA = bundle.getString("AbsA");
        disk = bundle.getString("disk");
        String_odm_d = bundle.getString("odm_d");

    }

}

