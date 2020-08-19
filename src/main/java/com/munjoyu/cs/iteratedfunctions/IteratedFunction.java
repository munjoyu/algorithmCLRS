package com.munjoyu.cs.iteratedfunctions;

import java.util.function.Function;

public class IteratedFunction {

    private static Simple f = new SimpleDivision();
    //private static Simple f = new SimpleSquareRoot();

    public static void main(String[] args) {
        //testSquareRoot();
        //testDivision();
        //testExponentation();
        testLogarithm();
    }

    public static void testSquareRoot() {
        f = new SimpleSquareRoot();
        final int c = 2;
        double n = 2222222222d;
        int i = (int) Math.ceil(Math.log(Math.log(n)));
        System.out.println("i = " + i + " : " + iterate_f(i, n));
        System.out.println("i = " + (i+1) + " : " + iterate_f(i+1, n));
        n = 22222222222d;
        i = (int) Math.ceil(Math.log(Math.log(n)));
        System.out.println("i = " + i + " : " + iterate_f(i, n));
        System.out.println("i = " + (i+1) + " : " + iterate_f(i+1, n));
    }

    public static void testDivision() {
        f = new SimpleDivision();
        final int c = 1;
        double n = 2222222222d;
        int i = (int) Math.ceil(Math.log(n));
        System.out.println("i = " + (i-1) + " : " + iterate_f(i-1, n));
        System.out.println("i = " + i + " : " + iterate_f(i, n));
        n = 222222222222d;
        i = (int) Math.ceil(Math.log(n));
        System.out.println("i = " + (i-1) + " : " + iterate_f(i-1, n));
        System.out.println("i = " + i + " : " + iterate_f(i, n));
    }

    public static void testLogarithm() {
        f = new SimpleLogarithm();
        double n = Math.pow(Math.E, Math.pow(Math.E, 6.5d));
        int least = getLeastIteratedLogarithm(1, n);
        System.out.println("LeastIteratedLogarithm = " + least);
        System.out.println("i = " + least + " : " + iterate_f(least, n));
        System.out.println("i = " + (least-1) + " : " + iterate_f(least-1, n));
    }

    private static int getLeastIteratedLogarithm(final double c, double n) {
        for(int i=1; i<100; i++) {
            double result = iterate_f(i, n);
            if(result <= c) return i;
        }
        throw new RuntimeException("too big number!!");
    }

    public static void testExponentation() {
        f = new SimpleExponentation();
        double n = 1.2d;
        for(int i=1; i<4; i++) {
            System.out.println(iterate_f(i, n));
        }
    }

    private static double iterate_f(int i, double n) {
        if (i < 0) {
            throw new RuntimeException("i should be non-negative!");
        } else if (i == 0) {
            return n;
        //} else if (i == 1) {
        //    return f.apply(n);
        } else {
            return f.apply(iterate_f(i-1, n));
        }
    }

}

interface Simple extends Function<Double, Double> {
}

class SimpleSubstract implements Simple {
    public Double apply(Double n) {
        return n-1;
    }
}

class SimpleDivision implements Simple {
    public Double apply(Double n) {
        return n/Math.E;
    }
}

class SimpleSquareRoot implements Simple {
    public Double apply(Double n) {
        return Math.sqrt(n);
    }
}

class SimpleExponentation implements Simple {
    public Double apply(Double n) {
        return Math.exp(n);
    }
}

class SimpleLogarithm implements Simple {
    public Double apply(Double n) {
        return Math.log(n);
    }
}
