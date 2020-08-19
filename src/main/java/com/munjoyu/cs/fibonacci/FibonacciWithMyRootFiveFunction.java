package com.munjoyu.cs.fibonacci;

import static java.lang.System.exit;

public class FibonacciWithMyRootFiveFunction {

    private static final double GOLDEN_RATIO = (1+Math.sqrt(5))/2.0d;
    private static final double CONSTANT = 2;

    private static long fibonacci(long n){
        if(n == 0 || n == 1) {
            return 1;
        } else {
            return Math.round(((double) CONSTANT) * Math.pow(GOLDEN_RATIO, n-1));
        }
    }

    public static void main(String args[]){
        for(int i=0; i<9; i++) {
            System.out.println("Value of number " + i + " in Fibonacci series->" + fibonacci(i));
        }
        long preTime=System.currentTimeMillis();
        System.out.println("Value of 120th number in Fibonacci series->"+fibonacci(120));
        long postTime=System.currentTimeMillis();
        System.out.println("Time taken to compute in milliseconds->"+(postTime-preTime));
    }
}

