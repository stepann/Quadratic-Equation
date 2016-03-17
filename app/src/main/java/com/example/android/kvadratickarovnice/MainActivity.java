package com.example.android.kvadratickarovnice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static android.widget.Toast.LENGTH_SHORT;


public class MainActivity extends AppCompatActivity {

    private EditText hodnotaA, hodnotaB, hodnotaC;
    private Button solver;
    private String A, B, C, B_2;
    public double a, b, c, d, x1, x2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //variables editText and Button
        hodnotaA = (EditText) findViewById(R.id.hodnotaA);
        hodnotaB = (EditText) findViewById(R.id.hodnotaB);
        hodnotaC = (EditText) findViewById(R.id.hodnotaC);
        solver = (Button) findViewById(R.id.button);

        solver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (controlZero() == true) {

                    Intent intent = new Intent(MainActivity.this, Solver_activity.class);

                    //parse String to Double
                    a = Double.parseDouble(A);
                    b = Double.parseDouble(B);
                    c = Double.parseDouble(C);
                    double b_2 = b*b;


                    //absolute values of b,c
                    double AbsA = a;
                    double AbsB = b;
                    double AbsC = c;



                    //if number is x.0 -> parsing to x
                    NumberFormat numberFormat = new DecimalFormat("#.###");

                    AbsA = Math.abs(a);

                    String absA = numberFormat.format(AbsA);
                    intent.putExtra("AbsA", absA);

                    //send signs od steps disk
                    if(a < 0) {
                        if(c < 0) {
                            intent.putExtra("disk", " -");
                            Log.e("..", "a i c je menší než 0, nastavuju -");
                        }
                    }
                    if(c < 0 ) {
                        if (a > 0) {
                            intent.putExtra("disk", " +");
                            Log.e(".", "c je menší než 0");
                        }
                    }
                    if(a < 0) {
                        if (c > 0) {
                            intent.putExtra("disk", " +");
                        }
                    }
                    if(a > 0) {
                        if (c > 0) {
                            intent.putExtra("disk", " -");
                        }
                    }
                    if(c == 0) {
                        intent.putExtra("disk", " -");
                    }

                    //Log.e("ah", "a, b ,c: " + a + " " + b + " " + c);

                    //formating A to decimal, if the A = x.0 -> parse to x and add ,
                    A = numberFormat.format(a);
                    intent.putExtra("kvadratickarovniceA", A);

                    B_2 = numberFormat.format(b_2);
                    intent.putExtra("b_2", B_2);

                    //finding out if B is negative or positive

                    if (b < 0) {
                        //make absolute value and send sign
                        AbsB = Math.abs(b);
                        B = numberFormat.format(AbsB);
                        intent.putExtra("kvadratickarovniceB", B);
                        intent.putExtra("symbolB", " - ");
                    } else {
                        B = numberFormat.format(AbsB);
                        intent.putExtra("kvadratickarovniceB", B);
                        intent.putExtra("symbolB", " + ");
                    }

                    //finding out if C is negative or positive
                    if (c < 0) {
                        //make absolute value and send sign
                        AbsC = Math.abs(c);
                        C = numberFormat.format(AbsC);
                        intent.putExtra("kvadratickarovniceC", C);
                        intent.putExtra("symbolC", "- ");

                    } else {
                        C = numberFormat.format(AbsC);
                        intent.putExtra("kvadratickarovniceC", C);
                        intent.putExtra("symbolC", "+ ");
                    }
                    //diskriminant
                    diskriminant();

                    String D = numberFormat.format(d);
                    intent.putExtra("diskriminant", " " + D);

                    if (d < 0) {
                        intent.putExtra("val", "negative");
                    }
                    if(d > 0){
                        double odm_d = Math.sqrt(d);
                        String String_odm_d = numberFormat.format(odm_d);
                        intent.putExtra("odm_d", String_odm_d);

                        //kořen první
                        root_1(); //method which counts first root
                        String string_x1 = numberFormat.format(x1);
                        intent.putExtra("x1", string_x1);
                        root_2(); //method which counts second root
                        String string_x2 = numberFormat.format(x2);
                        intent.putExtra("x2", string_x2);

                        intent.putExtra("val", "positive");
                    }
                    if (d == 0) {
                        double odm_d = Math.sqrt(d);
                        String String_odm_d = numberFormat.format(odm_d);
                        intent.putExtra("odm_d", String_odm_d);
                        root_1(); //method which counts first root
                        String string_x1 = numberFormat.format(x1);
                        intent.putExtra("x1", string_x1);

                        intent.putExtra("val", "zero");
                    }
                    //start intent and send data*//*
                    startActivity(intent);


                }


            }

        });
    }


    //create overflow menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                AboutItem();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //item About
    private void AboutItem() {
        new AlertDialog.Builder(this).setTitle(R.string.author).setMessage(R.string.aboutAuthor).setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }


    //method for control zeros from inputs, if A,B or C is zero, return false and Toast messege
    private boolean controlZero() {

        A = hodnotaA.getText().toString();
        B = hodnotaB.getText().toString();
        C = hodnotaC.getText().toString();


        //A must not be zero
        if (A.trim().isEmpty() || A.equals("0") || A.equals(".") || A.equals("-")) {
            Toast.makeText(MainActivity.this, R.string.errorA, LENGTH_SHORT).show();
            return false;
        }

        if (B.trim().isEmpty() || B.equals(".") || B.equals("-")) {
            Toast.makeText(MainActivity.this, R.string.errorB, LENGTH_SHORT).show();
            return false;
        }
        if (C.trim().isEmpty() || C.equals(".") || C.equals("-")) {
            Toast.makeText(MainActivity.this, R.string.errorC, LENGTH_SHORT).show();
            return false;
        }

        else return true;

    }
    //method for counting discriminant
    private double diskriminant() { return d = (b * b) - 4 * a * c;  }

    private double root_1() {
        return x1 = (-b + Math.sqrt(d)) / (2 * a);
    }

    private double root_2() {
        return x2 = (-b - Math.sqrt(d)) / (2 * a);
    }



}
