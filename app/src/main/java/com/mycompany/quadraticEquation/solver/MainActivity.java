package com.mycompany.quadraticEquation.solver;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
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


    private EditText edt_valueA, edt_valueB, edt_valueC;
    Button btn_solveEquation;
    Double dbl_a, dbl_b, dbl_c, dbl_discriminant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //editTexts and Button
        edt_valueA = (EditText) findViewById(R.id.edt_MainActivity_valueA);
        edt_valueB = (EditText) findViewById(R.id.edt_MainActivity_valueB);
        edt_valueC = (EditText) findViewById(R.id.edt_MainActivity_valueC);
        btn_solveEquation = (Button) findViewById(R.id.btn_MainActivity_solveEquation);

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

        solveEquation();
    }

    public void solveEquation() {
        btn_solveEquation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInputValues()) {
                    Intent intent = new Intent(MainActivity.this, SolverActivity.class);
                    CountEquation countEquation = new CountEquation();
                    NumberFormat numberFormat = new DecimalFormat("#.##");

                    dbl_a = Double.parseDouble(edt_valueA.getText().toString());
                    dbl_b = Double.parseDouble(edt_valueB.getText().toString());
                    dbl_c = Double.parseDouble(edt_valueC.getText().toString());
                    dbl_discriminant = countEquation.discriminant(dbl_a, dbl_b, dbl_c);

                    countEquation.discriminant(dbl_a, dbl_b, dbl_c);
                    countEquation.b_squared(dbl_b);
                    countEquation.first_root(dbl_a, dbl_discriminant, dbl_b);
                    countEquation.second_root(dbl_a, dbl_discriminant, dbl_b);
                    countEquation.absoluteValueA(dbl_a);
                    countEquation.sqrtDiscriminant(dbl_discriminant);
                    countEquation.numerator(dbl_b, dbl_a);
                    countEquation.denominator(dbl_a, dbl_discriminant);


                    String str_valueA = numberFormat.format(dbl_a);
                    String str_valueB = numberFormat.format(Math.abs(dbl_b));
                    String str_valueC = numberFormat.format(Math.abs(dbl_c));

                    if (dbl_b < 0) {
                        intent.putExtra("value_B", str_valueB);
                        intent.putExtra("symbol_B", " - ");
                    } else {
                        intent.putExtra("value_B", str_valueB);
                        intent.putExtra("symbol_B", " + ");
                    }

                    if (dbl_c < 0) {
                        intent.putExtra("value_C", str_valueC);
                        intent.putExtra("symbol_C", "- ");

                    } else {
                        intent.putExtra("value_C", str_valueC);
                        intent.putExtra("symbol_C", "+ ");
                    }


                    Double dbl_sqrt_discriminant_abs = Math.sqrt(Math.abs(dbl_discriminant));
                    //known if discriminant is zero, les than zero or more
                    if (dbl_discriminant < 0) {
                        String complex_discriminant = numberFormat.format(dbl_sqrt_discriminant_abs);
                        intent.putExtra("complex_discriminant", complex_discriminant);
                        intent.putExtra("value", "negative");
                        intent.putExtra("complex_first_root", countEquation.getComplexFirstRoot());
                        intent.putExtra("complex_second_root", countEquation.getComplexSecondRoot());

                    }
                    if (dbl_discriminant > 0) {
                        intent.putExtra("sqrt_discriminant", countEquation.getSqrtDiscriminant());
                        intent.putExtra("value", "positive");
                    }

                    if (dbl_discriminant == 0) {
                        intent.putExtra("sqrt_discriminant", "0");
                        intent.putExtra("value", "zero");
                    }


                    //intents
                    if ((dbl_c < 0 && dbl_a > 0) || (dbl_a < 0 && dbl_c > 0))
                        intent.putExtra("signInDiscriminantFormula", " +");
                    intent.putExtra("double_a", dbl_a);
                    intent.putExtra("double_b", dbl_b);
                    intent.putExtra("Abs_A", countEquation.getAbsoluteValueA());

                    intent.putExtra("value_A", str_valueA);
                    intent.putExtra("value_B", str_valueB);
                    intent.putExtra("value_C", str_valueC);

                    intent.putExtra("discriminant", countEquation.getDiscriminant());
                    intent.putExtra("sqrt_discriminant", countEquation.getSqrtDiscriminant());
                    intent.putExtra("b_squared", countEquation.getB_squared());
                    intent.putExtra("first_root", countEquation.getFirst_root());
                    intent.putExtra("second_root", countEquation.getSecond_root());

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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //item about Author
    private void AboutItem() {
        new AlertDialog.Builder(this).setTitle(R.string.author).setMessage(R.string.aboutAuthor).setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    private boolean checkInputValues() {

        String str_A = edt_valueA.getText().toString();
        String str_B = edt_valueB.getText().toString();
        String str_C = edt_valueC.getText().toString();

        //str_A must not be zero!
        if (str_A.trim().isEmpty() || str_A.equals("0") || str_A.equals("00") || str_A.equals("000") || str_A.equals("0000") || str_A.equals(".") || str_A.equals("-")) {
            Toast.makeText(MainActivity.this, R.string.err_valueA_is_Missing, LENGTH_SHORT).show();
            return false;
        }

        if (str_B.trim().isEmpty() || str_B.equals(".") || str_B.equals("-")) {
            Toast.makeText(MainActivity.this, R.string.err_valueB_is_Missing, LENGTH_SHORT).show();
            return false;
        }
        if (str_C.trim().isEmpty() || str_C.equals(".") || str_C.equals("-")) {
            Toast.makeText(MainActivity.this, R.string.err_ValueC_is_Missing, LENGTH_SHORT).show();
            return false;
        } else return true;

    }

}