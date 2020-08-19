package com.munjoyu.cs.matrices;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    public final Object[][] matrix;
    public Matrix(Object[][] matrix) {
        this.matrix = matrix;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(String row: toStringArray()) {
            sb.append(row + "\n");
        }
        return sb.toString();
    }

    public Matrix add(Matrix m) {
        if(matrix.length < 1) {
            throw new RuntimeException("This matrix is empty!");
        } else if(m.matrix.length < 1) {
            throw new RuntimeException("The given matrix is empty!");
        } else if(matrix.length != m.matrix.length || matrix[0].length != m.matrix[0].length) {
            throw new RuntimeException(
                            "This matrix cannot be added by the given matrix - they are not the same.");
        } else {
            if((matrix instanceof Integer[][]) && (m.matrix instanceof Integer[][])) {
                Integer[][] thisIntMatrix = (Integer[][]) matrix;
                Integer[][] thatIntMatrix = (Integer[][]) m.matrix;
                Integer[][] array = new Integer[thisIntMatrix.length][thisIntMatrix[0].length];
                for(int i=0; i<thisIntMatrix.length; i++) {
                    for(int j=0; j<thisIntMatrix[0].length; j++) {
                        array[i][j] = thisIntMatrix[i][j] + thatIntMatrix[i][j];
                    }
                }
                return new Matrix(array);
            } else if((matrix instanceof String[][]) && (m.matrix instanceof String[][])) {
                String[][] thisStringMatrix = (String[][]) matrix;
                String[][] thatStringMatrix = (String[][]) m.matrix;
                String[][] array = new String[thisStringMatrix.length][thisStringMatrix[0].length];
                for(int i=0; i<thisStringMatrix.length; i++) {
                    for(int j=0; j<thisStringMatrix[0].length; j++) {
                        array[i][j] = thisStringMatrix[i][j] + " + " + thatStringMatrix[i][j];
                    }
                }
                return new Matrix(array);
            } else if((matrix instanceof Matrix[][]) && (m.matrix instanceof Matrix[][])) {
                Matrix[][] thisMatrix = (Matrix[][]) matrix;
                Matrix[][] thatMatrix = (Matrix[][]) m.matrix;
                Matrix[][] array = new Matrix[thisMatrix.length][thatMatrix[0].length];
                for(int i=0; i<thisMatrix.length; i++) {
                    for(int j=0; j<thisMatrix[0].length; j++) {
                        array[i][j] = thisMatrix[i][j].add(thatMatrix[i][j]);
                    }
                }
                return new Matrix(array);
            } else {
                throw new RuntimeException("Unsupported multiplication!");
            }
        }
    }

    public Matrix multiply(Matrix m) {
        if(matrix.length < 1) {
            throw new RuntimeException("This matrix is empty!");
        } else if(m.matrix.length < 1) {
            throw new RuntimeException("The given matrix is empty!");
        } else if(matrix[0].length != m.matrix.length) {
            throw new RuntimeException(
                            "This matrix cannot be multiplied by the given matrix - the column size and the row size are not the same.");
        } else {
            if((matrix instanceof Integer[][]) && (m.matrix instanceof Integer[][])) {
                return multiplyWithStrassenAlgorithm(m);
                //return multiplyWithBruteForce(m);
            } else if((matrix instanceof String[][]) && (m.matrix instanceof String[][])) {
                String[][] thisStringMatrix = (String[][]) matrix;
                String[][] thatStringMatrix = (String[][]) m.matrix;
                String[][] array = new String[thisStringMatrix.length][thatStringMatrix[0].length];
                for(int i=0; i<thisStringMatrix.length; i++) {
                    for(int j=0; j<thatStringMatrix[0].length; j++) {
                        for(int k=0; k<thisStringMatrix[0].length; k++) {
                            array[i][j] = array[i][j] == null ? "  " + thisStringMatrix[i][k] + "*" + thatStringMatrix[k][j]: array[i][j] + " + " + thisStringMatrix[i][k] + "*" + thatStringMatrix[k][j];
                        }
                    }
                }
                return new Matrix(array);
            } else if((matrix instanceof Matrix[][]) && (m.matrix instanceof Matrix[][])) {
                Matrix[][] thisMatrix = (Matrix[][]) matrix;
                Matrix[][] thatMatrix = (Matrix[][]) m.matrix;
                Matrix[][] array = new Matrix[thisMatrix.length][thatMatrix[0].length];
                for(int i=0; i<thisMatrix.length; i++) {
                    for(int j=0; j<thatMatrix[0].length; j++) {
                        for(int k=0; k<thisMatrix[0].length; k++) {
                            array[i][j] = array[i][j] == null ? thisMatrix[i][k].multiply(thatMatrix[k][j]) : array[i][j].add(thisMatrix[i][k].multiply(thatMatrix[k][j]));
                        }
                    }
                }
                return new Matrix(array);
            } else {
                throw new RuntimeException("Unsupported multiplication!");
            }
        }
    }

    public Matrix multiplyWithStrassenAlgorithm(Matrix m) {
        Integer[][] thisIntMatrix = (Integer[][]) matrix;
        Integer[][] thatIntMatrix = (Integer[][]) m.matrix;
        if(matrix.length < 1 || m.matrix.length < 1) {
            throw new RuntimeException("Matrices cannot be empty!");
        } else if(matrix.length != matrix[0].length ||
                   matrix.length != m.matrix.length ||
                    m.matrix[0].length != m.matrix[0].length) {
            throw new RuntimeException("Matrices should be square and the same size!");
        } else if(matrix.length == 1 && m.matrix.length == 1) {
            Integer[][] array = new Integer[thisIntMatrix.length][thatIntMatrix[0].length];
            array[0][0] = thisIntMatrix[0][0] * thatIntMatrix[0][0];
            return new Matrix(array);
        } else {
            final int subMatrixSize = matrix.length/2;
            Integer[][][][] thisIntArrays = new Integer[2][2][subMatrixSize][subMatrixSize];
            Integer[][][][] thatIntArrays = new Integer[2][2][subMatrixSize][subMatrixSize];
            for(int i=0; i<matrix.length; i++) {
                for(int j=0; j<matrix[0].length; j++) {
                    if(i<subMatrixSize) {
                        if(j<subMatrixSize) {
                            thisIntArrays[0][0][i][j] = (Integer) matrix[i][j];
                            thatIntArrays[0][0][i][j] = (Integer) m.matrix[i][j];
                        } else {
                            thisIntArrays[0][1][i][j-subMatrixSize] = (Integer) matrix[i][j];
                            thatIntArrays[0][1][i][j-subMatrixSize] = (Integer) m.matrix[i][j];
                        }
                    } else {
                        if(j<subMatrixSize) {
                            thisIntArrays[1][0][i-subMatrixSize][j] = (Integer) matrix[i][j];
                            thatIntArrays[1][0][i-subMatrixSize][j] = (Integer) m.matrix[i][j];
                        } else {
                            thisIntArrays[1][1][i-subMatrixSize][j-subMatrixSize] = (Integer) matrix[i][j];
                            thatIntArrays[1][1][i-subMatrixSize][j-subMatrixSize] = (Integer) m.matrix[i][j];
                        }
                    }

                }
            }

            Matrix[][] thisIntMatrixArrays = new Matrix[2][2];
            thisIntMatrixArrays[0][0] = new Matrix(thisIntArrays[0][0]);
            thisIntMatrixArrays[0][1] = new Matrix(thisIntArrays[0][1]);
            thisIntMatrixArrays[1][0] = new Matrix(thisIntArrays[1][0]);
            thisIntMatrixArrays[1][1] = new Matrix(thisIntArrays[1][1]);

            Matrix[][] thatIntMatrixArrays = new Matrix[2][2];
            thatIntMatrixArrays[0][0] = new Matrix(thatIntArrays[0][0]);
            thatIntMatrixArrays[0][1] = new Matrix(thatIntArrays[0][1]);
            thatIntMatrixArrays[1][0] = new Matrix(thatIntArrays[1][0]);
            thatIntMatrixArrays[1][1] = new Matrix(thatIntArrays[1][1]);

            Matrix[][] array = new Matrix[][]{ {thisIntMatrixArrays[0][0].multiply(thatIntMatrixArrays[0][0]),
                                                thisIntMatrixArrays[0][1].multiply(thatIntMatrixArrays[0][1])},
                                               {thisIntMatrixArrays[1][0].multiply(thatIntMatrixArrays[1][0]),
                                                thisIntMatrixArrays[1][1].multiply(thatIntMatrixArrays[1][1])}};
        return new Matrix(array);
        }
    }

    public Matrix multiplyWithBruteForce(Matrix m) {
        Integer[][] thisIntMatrix = (Integer[][]) matrix;
        Integer[][] thatIntMatrix = (Integer[][]) m.matrix;
        Integer[][] array = new Integer[thisIntMatrix.length][thatIntMatrix[0].length];
        for(int i=0; i<thisIntMatrix.length; i++) {
            for(int j=0; j<thatIntMatrix[0].length; j++) {
                for(int k=0; k<thisIntMatrix[0].length; k++) {
                    array[i][j] = array[i][j] == null ? thisIntMatrix[i][k] * thatIntMatrix[k][j] : array[i][j] + thisIntMatrix[i][k] * thatIntMatrix[k][j];
                }
            }
        }
        return new Matrix(array);
    }

    public List<String> toStringArray() {
        List<String> ret = new ArrayList<>();
        if(matrix instanceof Integer[][]) {
            if (matrix.length > 0 && matrix[0].length > 0) {
                for (int i = 0; i < matrix.length; i++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("[");
                    for (int j = 0; j < matrix[0].length; j++) {
                        sb.append(String.format("%4d ", matrix[i][j]));
                    }
                    sb.append("]");
                    ret.add(sb.toString());
                }
                return ret;
            } else {
                ret.add("[]");
                return ret;
            }
        } else if(matrix instanceof String[][]) {
            if (matrix.length > 0 && matrix[0].length > 0) {
                for (int i = 0; i < matrix.length; i++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("[");
                    for (int j = 0; j < matrix[0].length; j++) {
                        sb.append(matrix[i][j]);
                    }
                    sb.append("]");
                    ret.add(sb.toString());
                }
                return ret;
            } else {
                ret.add("[]");
                return ret;
            }
        } else if(matrix instanceof  Matrix[][]) {
            Matrix[][] matrixOfMatrices = (Matrix[][]) matrix;
            if (matrixOfMatrices.length > 0 && matrixOfMatrices[0].length > 0) {
                for (int i = 0; i < matrixOfMatrices.length; i++) {
                    List<List<String>> ll = new ArrayList<>();
                    for (int j = 0; j < matrixOfMatrices[0].length; j++) {
                        ll.add(matrixOfMatrices[i][j].toStringArray());
                    }
                    for (int k = 0; k < ll.get(0).size(); k++) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("[");
                        for (int j = 0; j < ll.size(); j++) {
                            sb.append(ll.get(j).get(k));
                        }
                        sb.append("]");
                        ret.add(sb.toString());
                    }
                }
                return ret;
            } else {
                ret.add("[]");
                return ret;
            }
        } else {
            throw new RuntimeException("Unsupported matrix!");
        }
    }
}


