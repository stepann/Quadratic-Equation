package com.mycompany.quadraticEquation.solver;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
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

    public double double_a, double_b, double_c, double_discriminant, double_root_first, double_root_second,
            double_rooted_discriminant, double_rooted_discriminant_complex, double_AbsoluteValueA, double_AbsoluteValueB, double_AbsoluteValueC;
    private EditText edt_valueA, edt_valueB, edt_valueC;
    private String String_A, String_B, String_C, B_SQUARED, COMPLEX_ROOT_FIRST, COMPLEX_ROOT_SECOND, COMPLEX_DISCRIMINANT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //variables editText and Button
        edt_valueA = (EditText) findViewById(R.id.edt_MainActivity_valueA);
        edt_valueB = (EditText) findViewById(R.id.edt_MainActivity_valueB);
        edt_valueC = (EditText) findViewById(R.id.edt_MainActivity_valueC);

        edt_valueA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String txt = s.toString();
                if (txt.contains("-") && txt.contains("."))
                    edt_valueA.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
                if (txt.contains(".") && !txt.contains("-") || (!txt.contains(".") && txt.contains("-")))
                    edt_valueA.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
                if (!txt.contains(".") && !txt.contains("-"))
                    edt_valueA.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});

            }
        });

        edt_valueB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String txt = s.toString();
                if (txt.contains("-") && txt.contains("."))
                    edt_valueB.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
                if (txt.contains(".") && !txt.contains("-") || (!txt.contains(".") && txt.contains("-")))
                    edt_valueB.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
                if (!txt.contains(".") && !txt.contains("-"))
                    edt_valueB.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
            }
            });
        edt_valueC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String txt = s.toString();
                if (txt.contains("-") && txt.contains("."))
                    edt_valueC.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
                if (txt.contains(".") && !txt.contains("-") || (!txt.contains(".") && txt.contains("-")))
                    edt_valueC.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
                if (!txt.contains(".") && !txt.contains("-"))
                    edt_valueC.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
            }
        });

        Button btn_solveEquation = (Button) findViewById(R.id.btn_MainActivity_solveEquation);

        assert btn_solveEquation != null;
        btn_solveEquation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkInputValues()) {

                    Intent intent = new Intent(MainActivity.this, SolverActivity.class);
                    NumberFormat numberFormat = new DecimalFormat("#.##");

                    //parse String to Double
                    double_a = Double.parseDouble(String_A);
                    double_b = Double.parseDouble(String_B);
                    double_c = Double.parseDouble(String_C);

                    intent.putExtra("double_a", double_a);
                    intent.putExtra("double_b", double_b);

                    B_SQUARED = numberFormat.format(double_b * double_b);
                    intent.putExtra("b_squared", B_SQUARED);

                    if (double_c < 0 && double_a > 0)
                        intent.putExtra("signInDiscriminantFormula", " +");
                    if (double_a < 0 && double_c > 0)
                        intent.putExtra("signInDiscriminantFormula", " +");

                    double_AbsoluteValueA = Math.abs(double_a);
                    String String_AbsoluteValueA = numberFormat.format(double_AbsoluteValueA);
                    intent.putExtra("AbsA", String_AbsoluteValueA);

                    String_A = numberFormat.format(double_a);
                    intent.putExtra("kvadratickarovniceA", String_A);

                    if (double_b < 0) {
                        //make absolute value and sent sign
                        String_B = numberFormat.format(Math.abs(double_b));
                        intent.putExtra("kvadratickarovniceB", String_B);
                        intent.putExtra("symbolB", " - ");
                    } else {
                        String_B = numberFormat.format(Math.abs(double_b));
                        intent.putExtra("kvadratickarovniceB", String_B);
                        intent.putExtra("symbolB", " + ");
                    }

                    if (double_c < 0) {
                        //make absolute value and send sign
                        String_C = numberFormat.format(Math.abs(double_c));
                        intent.putExtra("kvadratickarovniceC", String_C);
                        intent.putExtra("symbolC", "- ");

                    } else {
                        String_C = numberFormat.format(Math.abs(double_c));
                        intent.putExtra("kvadratickarovniceC", String_C);
                        intent.putExtra("symbolC", "+ ");
                    }

                    //counting discriminant
                    discriminant();

                    double_rooted_discriminant = Math.sqrt(double_discriminant);
                    double_rooted_discriminant_complex = Math.sqrt(Math.abs(double_discriminant));

                    String String_Discriminant = numberFormat.format(double_discriminant);
                    intent.putExtra("diskriminant", " " + String_Discriminant);

                    if (double_discriminant < 0) {
                        COMPLEX_DISCRIMINANT = numberFormat.format(double_rooted_discriminant_complex);
                        intent.putExtra("COMPLEX_DISCRIMINANT", COMPLEX_DISCRIMINANT);
                        count_complex_roots(); //method for counting roots in complex numbers
                        intent.putExtra("val", "negative");
                        intent.putExtra("complex_1", COMPLEX_ROOT_FIRST);
                        intent.putExtra("complex_2", COMPLEX_ROOT_SECOND);

                    }
                    if (double_discriminant > 0) {
                        String String_rooted_discriminant = numberFormat.format(double_rooted_discriminant);
                        intent.putExtra("double_rooted_discriminant", String_rooted_discriminant);

                        //count first root
                        first_root(); //method which counts first root
                        String string_x1 = numberFormat.format(double_root_first);
                        intent.putExtra("double_root_first", string_x1);
                        second_root(); //method which counts second root
                        String string_x2 = numberFormat.format(double_root_second);
                        intent.putExtra("double_root_second", string_x2);
                        intent.putExtra("val", "positive");
                    }

                    if (double_discriminant == 0) {
                        String String_rooted_discriminant = numberFormat.format(double_rooted_discriminant);
                        intent.putExtra("double_rooted_discriminant", String_rooted_discriminant);
                        first_root(); //method which counts first root
                        String string_x1 = numberFormat.format(double_root_first);
                        intent.putExtra("double_root_first", string_x1);
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

    private boolean checkInputValues() {

        String_A = edt_valueA.getText().toString();
        String_B = edt_valueB.getText().toString();
        String_C = edt_valueC.getText().toString();

        //String_A must not be zero
        if (String_A.trim().isEmpty() || String_A.equals("0") || String_A.equals("00") || String_A.equals("000")|| String_A.equals("0000") || String_A.equals(".") || String_A.equals("-")) {
            //edt_valueA.setError(getString(R.string.err_valueA_is_Missing)); black screen
            Toast.makeText(MainActivity.this, R.string.err_valueA_is_Missing, LENGTH_SHORT).show();
            return false;
        }

        if (String_B.trim().isEmpty() || String_B.equals(".") || String_B.equals("-")) {
            Toast.makeText(MainActivity.this, R.string.err_valueB_is_Missing, LENGTH_SHORT).show();
            return false;
        }
        if (String_C.trim().isEmpty() || String_C.equals(".") || String_C.equals("-")) {
            Toast.makeText(MainActivity.this, R.string.err_ValueC_is_Missing, LENGTH_SHORT).show();
            return false;
        } else return true;

    }

    //method for counting discriminant
    private double discriminant() {
        return double_discriminant = (double_b * double_b) - 4 * double_a * double_c;
    }

    private double first_root() {
        return double_root_first = (-double_b + Math.sqrt(double_discriminant)) / (2 * double_a);
    }

    private double second_root() {
        return double_root_second = (-double_b - Math.sqrt(double_discriminant)) / (2 * double_a);
    }

    private void count_complex_roots() {
        NumberFormat numberFormat = new DecimalFormat("#.##");
        double numerator = (-double_b / (2 * double_a));
        double denominator = Math.sqrt((Math.abs(double_discriminant))) / (2 * double_a);
        COMPLEX_ROOT_FIRST = numberFormat.format(numerator) + " + " + numberFormat.format(Math.abs(denominator)) + "i";
        COMPLEX_ROOT_SECOND = numberFormat.format(numerator) + " - " + numberFormat.format(Math.abs(denominator)) + "i";
    }
}
