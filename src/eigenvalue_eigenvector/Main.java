package eigenvalue_eigenvector;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final double eps = 0.001;

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Введите размерность матрицы: ");
        int n = reader.nextInt();

        System.out.println("\tЗаполните матрицу:");
        double[][] A = new double[n][n];
        fillMatrix(A, reader);
        print(A);

        double[][] H = diagonalMatrix(n);

        List<double[][]> Vecs = new ArrayList<>();

        while (true){
            double[] diagMax = DiagMax.find(A);
            double max = diagMax[0];
            int iInd = (int) diagMax[1];
            int jInd = (int) diagMax[2];

            if (diagMax[0] > eps){
                double fi = 0.5 * Math.atan((2 * max) / (A[iInd][iInd] - A[jInd][jInd]));
                double sinFi = Math.sin(fi);
                double cosFi = Math.cos(fi);
                H = modeH(H, sinFi, cosFi, iInd, jInd);

                A = modeA(A, H);

                Vecs.add(H);
                H = diagonalMatrix(n);
            } else
                break;
        }

        printEigenvalues(A, n);

        double[][] superVec;
        if (Vecs.size() > 1){
            superVec = calculateSuperVec(Vecs);
        } else {
            superVec = Vecs.get(0);
        }

        printEigenvectors(superVec, n);

    }

    private static void fillMatrix(double[][] M, Scanner reader){
        for (int i = 0; i < M[0].length; i++){
            for (int j = 0; j < M[0].length; j++){
                System.out.printf("A[%d][%d] = ", i, j);
                M[i][j] = reader.nextDouble();
            }
        }
    }

    private static double[][] modeH(double[][] H, double sin, double cos, int i, int j){
        H[i][i] = cos;
        H[i][j] = -sin;
        H[j][i] = sin;
        H[j][j] = cos;

        return H;
    }

    private static double[][] modeA(double[][] A, double[][] H){
        double[][] result = Transpose.perform(H);
        result = MatrixMultiplection.calculate(result, A);
        result = MatrixMultiplection.calculate(result, H);

        return result;
    }

    private static double[][] diagonalMatrix(int n){
        double[][] H =  new double[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (i == j) H[i][j] = 1;
            }
        }

        return H;
    }

    private static void print(double[][] M){
        System.out.println("\tМатрица:");
        for (double[] row : M){
            for (double col : row){
                System.out.printf("%5.2f; ", col);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void printEigenvalues(double[][] M, int n){
        for (int i = 0; i < n; i++){
            for (int j =0; j < n; j++){
                if (i == j)
                    System.out.printf("собственное значение №%d: %.4f\n", i + 1, M[i][j]);
            }
        }
        System.out.println();
    }

    private static void printEigenvectors(double[][] M, int n){
        for (int j = 0; j < n; j++){
            System.out.printf("собственный вектор №%d: \n", j + 1);
            for (int i = 0; i < n; i++){
                System.out.printf("                       |%8.4f|\n", M[i][j]);
            }
            System.out.println();
        }
    }

    private static double[][] calculateSuperVec(List<double[][]> Vecs){
        double[][] result = Vecs.get(0);

        for (int i = 1; i < Vecs.size(); i++){
            result = MatrixMultiplection.calculate(result, Vecs.get(i));
        }

        return result;
    }
}
