package com.munjoyu.cs.matrices;

public class BruteForceMethodTest {
    static final Integer[][] intArrayA = new Integer[][] { { 11, 12, 13, 14, 15, 16, 17, 18 }
                                                        , { 21, 22, 23, 24, 25, 26, 27, 28 }
                                                        , { 31, 32, 33, 34, 35, 36, 37, 38 }
                                                        , { 41, 42, 43, 44, 45, 46, 47, 48 }
                                                        , { 51, 52, 53, 54, 55, 56, 57, 58 }
                                                        , { 61, 62, 63, 64, 65, 66, 67, 68 }
                                                        , { 71, 72, 73, 74, 75, 76, 77, 78 }
                                                        , { 81, 82, 83, 84, 85, 86, 87, 88 }
                                                        };
    static final Integer[][] intArrayB = intArrayA;

    private static final Matrix intMA = new Matrix(intArrayA);
    private static final Matrix intMB = new Matrix(intArrayB);


    public static void main(String[] args) {
        //Integer[][] array = new Integer[2][2];
        //array[0][0] = 1;
        //array[0][1] = 2;

        testIntMatrixMultiplication();
    }

    private static void testIntMatrixMultiplication() {
        System.out.println((intMA.multiply(intMB)).toString());
    }
}

