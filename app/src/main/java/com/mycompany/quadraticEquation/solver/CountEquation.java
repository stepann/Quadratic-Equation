package com.mycompany.quadraticEquation.solver;


import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CountEquation {

    NumberFormat numberFormat = new DecimalFormat("#.##");

    Double discriminant, b_squared, first_root, second_root, absoluteValueA, sqrtDiscriminant, numerator, denominator;
    String str_discriminant, str_b_squared, str_first_root, str_second_root, str_absoluteValueA,
            str_sqrt_discriminant, complex_first_root, complex_second_root;

    CountEquation() {
    }

    public Double discriminant(Double value_A, Double value_B, Double value_C) {
        return discriminant = (value_B * value_B) - 4 * value_A * value_C;
    }

    public double b_squared(Double value_B) {
        return b_squared = value_B * value_B;
    }

    public Double first_root(Double value_B, Double discriminant, Double value_A) {
        return first_root = (-value_B + Math.sqrt(discriminant)) / (2 * value_A);
    }

    public Double second_root(Double value_B, Double discriminant, Double value_A) {
        return second_root = (-value_B - Math.sqrt(discriminant)) / (2 * value_A);
    }

    public Double absoluteValueA(Double value_A) {
        return absoluteValueA = Math.abs(value_A);
    }

    public Double sqrtDiscriminant(Double discriminant) {
        return sqrtDiscriminant = Math.sqrt(discriminant);
    }

    public Double numerator(Double value_B, Double value_A) {
        return numerator = (-value_B / (2 * value_A));
    }

    public Double denominator(Double value_A, Double dbl_discriminant) {
        return denominator = Math.sqrt((Math.abs(dbl_discriminant))) / (2 * value_A);
    }


    //String methods
    public String getFirst_root() {
        return str_first_root = numberFormat.format(first_root);
    }

    public String getSecond_root() {
        return str_second_root = numberFormat.format(second_root);
    }

    public String getDiscriminant() {
        return str_discriminant = numberFormat.format(discriminant);
    }

    public String getB_squared() {
        return str_b_squared = numberFormat.format(b_squared);
    }

    public String getAbsoluteValueA() {
        return str_absoluteValueA = numberFormat.format(absoluteValueA);
    }

    public String getSqrtDiscriminant() {
        return str_sqrt_discriminant = numberFormat.format(sqrtDiscriminant);
    }

    public String getComplexFirstRoot() {
        return complex_first_root = numberFormat.format(numerator) + " + " + numberFormat.format(Math.abs(denominator)) + "i";
    }

    public String getComplexSecondRoot() {
        return complex_second_root = numberFormat.format(numerator) + " - " + numberFormat.format(Math.abs(denominator)) + "i";
    }


}
