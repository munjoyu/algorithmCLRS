package com.munjoyu.cs;

import java.util.Arrays;

public class Sorting
{
    private static final int SIZE = 1000000;
    public static void main( String[] args ) {

//        for(int i=1; i<10000; i++) {
//            System.out.println(i + " : " + i * binlog(i));
//        }

        long[] masterNumbers = new long[SIZE];
        populateWithRandomNumbers(masterNumbers);
        System.out.println( "Unsorted: " );
        Arrays.stream(masterNumbers).limit(100).forEach(i -> System.out.print(i + ", "));
        //runInsertionSort(masterNumbers);
        runMergeSort(masterNumbers);
    }

    private static void runInsertionSort(final long[] masterNumbers) {
        long[] numbers = Arrays.copyOf(masterNumbers, masterNumbers.length);
        System.out.println();
        long start = System.currentTimeMillis();
        insertionSort(numbers);
        printResult("Insertion", numbers, start);
    }

    private static void runMergeSort(final long[] masterNumbers) {
        long[] numbers = Arrays.copyOf(masterNumbers, masterNumbers.length);
        System.out.println();
        long start = System.currentTimeMillis();
        mergeSort(numbers, 0, numbers.length-1);
        printResult("Merge", numbers, start);
    }

    private static void printResult(String algorithName, long[] numbers, long start) {
        System.out.println( algorithName + " sorting took " + (System.currentTimeMillis() - start) + " milliseconds." );
        System.out.println( "Sorted: " );
        Arrays.stream(numbers).limit(100).forEach(i -> System.out.print(i + ", "));
        System.out.println();
    }

    private static void insertionSort(final long[] numbers) {
        long key;
        for(int i=1; i<numbers.length; i++) {
            key = numbers[i];
            int j = i - 1;
            while(j>=0 && numbers[j] > key) {
                numbers[j+1] = numbers[j];
                j--;
            }
            numbers[j+1] = key;
        }
    }

    private static void mergeSort(final long[] numbers, final int p, final int r) {
        if(r > p) {
            int q = (p + r) / 2;
            mergeSort(numbers, p, q);
            mergeSort(numbers, q + 1, r);
            //merge(numbers, p, q, r);
            mergeWithoutSentinels(numbers, p, q, r);
        }
    }

    private static void merge(final long[] numbers, final int p, final int q, final int r) {
        final int n1 = q - p + 1;
        final int n2 = r - q;

        long[] left, right;
        left = new long[n1+1];
        right = new long[n2+1];
        for(int i=0; i<n1; i++) left[i] = numbers[p+i];
        for(int j=0; j<n2; j++) right[j] = numbers[q+1+j];
        left[n1] = Long.MAX_VALUE;
        right[n2] = Long.MAX_VALUE;
        int i = 0;
        int j = 0;
        for(int k=p; k<=r; k++) {
            if(left[i] <= right[j]) {
                numbers[k] = left[i];
                i++;
            } else {
                numbers[k] = right[j];
                j++;
            }
        }
    }

    private static void mergeWithoutSentinels(final long[] numbers, final int p, final int q, final int r) {
        final int n1 = q - p + 1;
        final int n2 = r - q;

        long[] left, right;
        left = new long[n1];
        right = new long[n2];
        for(int i=0; i<n1; i++) left[i] = numbers[p+i];
        for(int j=0; j<n2; j++) right[j] = numbers[q+1+j];
        int i = 0;
        int j = 0;
        for(int k=p; k<=r; k++) {
            if(j>=right.length && i<left.length) {
                numbers[k] = left[i];
                i++;
            } else if(i>=left.length && j<left.length) {
                numbers[k] = right[j];
                j++;
            } else if(left[i] <= right[j]) {
                numbers[k] = left[i];
                i++;
            } else {
                numbers[k] = right[j];
                j++;
            }
        }
    }

    private static void populateWithRandomNumbers(long[] numbers) {
        for(int i=0; i<numbers.length; i++) {
            numbers[i] = (long) (Math.random() * SIZE );
        }
    }

    public static int binlog( int bits ) // returns 0 for bits=0
    {
        int log = 0;
        if( ( bits & 0xffff0000 ) != 0 ) { bits >>>= 16; log = 16; }
        if( bits >= 256 ) { bits >>>= 8; log += 8; }
        if( bits >= 16  ) { bits >>>= 4; log += 4; }
        if( bits >= 4   ) { bits >>>= 2; log += 2; }
        return log + ( bits >>> 1 );
    }

}
