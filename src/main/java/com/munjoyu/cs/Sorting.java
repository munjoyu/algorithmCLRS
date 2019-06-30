package com.munjoyu.cs;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class Sorting
{
    static final int SIZE = 100000000;
    public static void main( String[] args )
    {
        long[] numbers = new long[SIZE];
        populateWithRandomNumbers(numbers);
        System.out.println( "Unsorted: " );
        Arrays.stream(numbers).limit(100).forEach(i -> System.out.print(i + ", "));
        System.out.println();
        insertionSort(numbers);
        System.out.println( "Sorted: " );
        Arrays.stream(numbers).limit(100).forEach(i -> System.out.print(i + ", "));
        System.out.println();
    }

    private static void insertionSort(long[] numbers) {
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

    private static void populateWithRandomNumbers(long[] numbers) {
        for(int i=0; i<numbers.length; i++) {
            numbers[i] = (long) (Math.random() * SIZE );
        }
    }
}
