package com.munjoyu.cs.matrices;

public class MatrixTest {
    private static final Integer[][] intArray = new Integer[][]{{1,2,3}, {4,5,6}, {7,8,9}};
    private static final Integer[][] intArrayA = intArray;
    private static final Integer[][] intArrayB = new Integer[][]{{11,12,13}, {14,15,16}, {17,18,19}};
    private static final Integer[][] intArrayC = new Integer[][]{{21,22,23}, {24,25,26}, {27,28,29}};
    private static final Integer[][] intArrayD = new Integer[][]{{31,32,33}, {34,35,36}, {37,38,39}};

    private static final Matrix intM = new Matrix(intArray);

    private static final Matrix intMA = new Matrix(intArrayA);
    private static final Matrix intMB = new Matrix(intArrayB);
    private static final Matrix intMC = new Matrix(intArrayC);
    private static final Matrix intMD = new Matrix(intArrayD);

    private static final Matrix[][] intMatrixArray = new Matrix[][]{ { new Matrix(intArrayA), new Matrix(intArrayB)},
                                                             {new Matrix(intArrayC),new Matrix(intArrayD)}};
    private static final Matrix intMM = new Matrix(intMatrixArray);
    private static final Matrix intMMA = intMM;
    private static final Matrix intMMB = intMM;

    private static final Matrix[][] intMatrixMatrix = new Matrix[][]{ { new Matrix(intMatrixArray), new Matrix(intMatrixArray)},
                                                              { new Matrix(intMatrixArray), new Matrix(intMatrixArray)}};
    private static final Matrix mmm = new Matrix(intMatrixMatrix);
    private static final Matrix[][] mmMatrix = new Matrix[][]{{ new Matrix(intMatrixMatrix), new Matrix(intMatrixMatrix)},
                                          { new Matrix(intMatrixMatrix), new Matrix(intMatrixMatrix)}};
    private static final Matrix mmmm = new Matrix(mmMatrix);

    private static final String[][] stringArrayA = new String[][]{{"a11","a12","a13"}, {"a21","a22","a23"}, {"a31","a32","a33"}};
    private static final String[][] stringArrayB = new String[][]{{"b11","b12","b13"}, {"b21","b22","b23"}, {"b31","b32","b33"}};
    private static final String[][] stringArrayC = new String[][]{{"c11","c12","c13"}, {"c21","c22","c23"}, {"c31","c32","c33"}};
    private static final String[][] stringArrayD = new String[][]{{"d11","d12","d13"}, {"d21","d22","d23"}, {"d31","d32","d33"}};

    private static final Matrix stringMA = new Matrix(stringArrayA);
    private static final Matrix stringMB = new Matrix(stringArrayB);
    private static final Matrix stringMC = new Matrix(stringArrayC);
    private static final Matrix stringMD = new Matrix(stringArrayD);

    private static final Matrix[][] stringMatrixArray = new Matrix[][]{ { new Matrix(stringArrayA), new Matrix(stringArrayB)},
                                                             {new Matrix(stringArrayC),new Matrix(stringArrayD)}};
    private static final Matrix stringMM = new Matrix(stringMatrixArray);
    private static final Matrix stringMMA = stringMM;
    private static final Matrix stringMMB = stringMM;

    public static void main(String[] args) {
        //testTrivialCases();
        testScalaCases();
        //testIntMatrixPrint();
        //testNestedMatrixPrint();
        //testIntMatrixMultiplication();
        //testStringMatrixMultiplication();
    }

    private static void testTrivialCases() {
        Integer[][] zeroIntArray = new Integer[0][0];
        Matrix zeroM = new Matrix(zeroIntArray);
        System.out.println(zeroM.toString());

        Matrix[][] zeroMatrixArray = new Matrix[0][0];
        zeroM = new Matrix(zeroMatrixArray);
        System.out.println(zeroM.toString());
    }


    private static void testScalaCases() {
        Integer[][] scalaIntArray = new Integer[1][1];
        scalaIntArray[0][0] = 2;
        Matrix scalaM = new Matrix(scalaIntArray);
        System.out.println(scalaM.toString());

        Matrix[][] scalaMatrixArray = new Matrix[1][1];
        scalaMatrixArray[0][0] = new Matrix(scalaIntArray);
        scalaM = new Matrix(scalaMatrixArray);
        System.out.println(scalaM.toString());

        Integer[][] scalaIntArrayA = scalaIntArray;
        Integer[][] scalaIntArrayB = new Integer[1][1];
        scalaIntArrayB[0][0] = 3;
        Matrix scalaMA = new Matrix(scalaIntArrayA);
        Matrix scalaMB = new Matrix(scalaIntArrayB);
        System.out.println(scalaMA.add(scalaMB).toString());
        System.out.println(scalaMA.multiply(scalaMB).toString());
    }

    private static void testIntMatrixPrint() {
        System.out.println(intM.toString());
    }

    private static void testIntMatrixMultiplication() {
        System.out.println((intMA.multiply(intMB)).toString());
        System.out.println((intMMA.multiply(intMMB)).toString());
        System.out.println((intMA.multiply(intMA)).toString());
        System.out.println((intMB.multiply(intMC)).toString());
    }
    private static void testStringMatrixMultiplication() {
        System.out.println((stringMA.multiply(stringMB)).toString());
        System.out.println((stringMMA.multiply(stringMMB)).toString());
    }

    private static void testNestedMatrixPrint() {
        System.out.println(intMM.toString());
        //System.out.println(mmm.toString());
        //System.out.println(mmmm.toString());
    }
}

