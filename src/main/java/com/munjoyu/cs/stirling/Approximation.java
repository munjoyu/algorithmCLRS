package com.munjoyu.cs.stirling;

public class Approximation {

    public static void main(String[] args) {
        //for(long l=0; l<11; l++) {
        //    approximateFactorial(l);
        //}
        System.out.println(approximateSquareRoot(3.0, 1.0d));
        System.out.println(approximateCubicRoot(3.0, 1.0d));
        System.out.println(approximateQuinticRoot(3.0, 1.3d));
    }

    private static void approximateFactorial(long n) {
        long result = (long) Math.ceil(Math.sqrt(2 * Math.PI * n) * Math.pow(n/Math.E, (double) n) * Math.pow(Math.E, 1/(12*n + 0.9)));
        System.out.println(n + ": " + result);
    }

    private static double approximateSquareRoot(double r, double x) {
        double result = (x + r/x) / 2;
        //System.out.println(x + ": " + result);
        if(result != x) return approximateSquareRoot(r, result);
        else return result;
    }

    private static double approximateCubicRoot(double r, double x) { // The square root algorithm above doesn't work here
        double result = (x + r/(x*x)) / 2;
        //System.out.println(x + ": " + result);
        if(result != x) return approximateCubicRoot(r, result);
        else return result;
    }

    private static double approximateQuinticRoot(double r, double x) { // The square root algorithm above doesn't work here
        // The algorithm needs a prrof
        double result = (x + r/Math.pow(x, 4)) / Math.sqrt(r);
        System.out.println(x + ": " + result);
        if(result != x) return approximateQuinticRoot(r, result);
        else return result;
    }

}
