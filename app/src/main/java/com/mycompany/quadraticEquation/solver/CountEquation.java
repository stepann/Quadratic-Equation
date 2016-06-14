package com.mycompany.quadraticEquation.solver;


import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CountEquation {

    NumberFormat numberFormat = new DecimalFormat("#.##");
    Double discriminant, b_squared, first_root, second_root, absoluteValueA, sqrtDiscriminant, numerator, denominator;
    public String str_discriminant, str_b_squared, str_first_root, str_second_root, str_absoluteValueA,
            str_sqrt_discriminant, complex_first_root, complex_second_root;

    CountEquation() {
    }

    public Double discriminant(Double dbl_a, Double dbl_b, Double dbl_c) {
        return discriminant = (dbl_b * dbl_b) - 4 * dbl_a * dbl_c;
    }

    public double b_squared(Double dbl_b) {
        return b_squared = dbl_b * dbl_b;
    }

    public Double first_root(Double dbl_b, Double discriminant, Double dbl_a) {
        return first_root = (-dbl_b + Math.sqrt(discriminant)) / (2 * dbl_a);
    }

    public Double second_root(Double dbl_b, Double discriminant, Double dbl_a) {
        return second_root = (-dbl_b - Math.sqrt(discriminant)) / (2 * dbl_a);
    }

    public Double absoluteValueA(Double dbl_a) {
        return absoluteValueA = Math.abs(dbl_a);
    }

    public Double sqrtDiscriminant(Double discriminant) {
        return sqrtDiscriminant = Math.sqrt(discriminant);
    }

    public Double numerator(Double dbl_b, Double dbl_a) {
        return numerator = (-dbl_b / (2 * dbl_a));
    }

    public Double denominator(Double dbl_a, Double dbl_discriminant) {
        return denominator = Math.sqrt((Math.abs(dbl_discriminant))) / (2 * dbl_a);
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
