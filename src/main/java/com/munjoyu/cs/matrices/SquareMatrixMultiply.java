package com.munjoyu.cs.matrices;

public class SquareMatrixMultiply {

    private static final int[][] MatrixA = {{1,2,3}, {4,5,6}, {7,8,9}};

    public static void main(String[] args) {
        printMatrix(multiplySquareMatrices(MatrixA, MatrixA));
    }

    private static int[][] multiplySquareMatrices(int[][] matrixA, int[][] matrixB) {
        if(matrixA.length>0 && matrixA.length == matrixA[0].length &&
             matrixA.length == matrixB.length && matrixB.length == matrixB[0].length) {
            int[][] result = new int[matrixA.length][matrixA.length];
            for(int i=0; i<result.length; i++) {
                for(int j=0; j<result.length; j++) {
                    result[i][j] = 0;
                    for(int k=0; k<result.length; k++) {
                        result[i][j] += matrixA[i][k] * matrixA[k][j];
                    }
                }
            }
            return result;
        } else {
            throw new RuntimeException("Empty or mismatched matrices!");
        }
    }

    private static void printMatrix(int[][] matrix) {
        if(matrix.length>0 && matrix[0].length>0) {
            for(int i=0; i<matrix.length; i++) {
                System.out.print("[ ");
                for(int j=0; j<matrix[0].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println("]");
            }
        } else {
            System.out.println("The matrix is empty!!");
        }
    }
}
