package Koshi;

import java.util.Scanner;

public class Main {
    private static int ny;

    static double[] FPR(double x, double[] Y){
        double[] F = new double[ny];
        F[0] = Y[0] / x + Y[1] - Math.pow(Math.E, x);
        F[1] = (((2 * x) / Y[0]) + (Math.pow(Y[1], 2) / Math.pow(Math.E, x))) - 1;
        return F;
    }

    private static void OUT(double x, double[] Y){
        System.out.printf("x=%.4f  y1=%.6f  u1=%.6f  d1=%.6f  y2=%.6f  u2=%.6f  d2=%.6f\n",
                x, Y[0], 2 * x, (2 * x) - Y[0], Y[1], Math.pow(Math.E, x), Math.pow(Math.E, x) - Y[1]);
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        System.out.println("\nВведите интервал значений x:");
        System.out.print("\ta:=");
        double a = reader.nextDouble();
        System.out.print("\tb:=");
        double b = reader.nextDouble();
        System.out.print("\nВведите количество шагов сетки: ");
        int nx = reader.nextInt();
        System.out.print("\nВведите количество уравнений: ");
        ny = reader.nextInt();

        double h = (b - a) / nx;
        double X = a;

        double[][] Y = new double[nx + 1][ny];
        double[][] F = new double[nx + 1][ny];
        double[][] DF = new double[nx + 1][ny];
        double[][] D2F = new double[nx + 1][ny];
        double[][] D3F = new double[nx + 1][ny];

        Y[0][0] = a * 2;
        Y[0][1] = Math.pow(Math.E, a);
        F[0] = FPR(X, Y[0]);
        OUT(X, Y[0]);

        Y[1] = Rungie_Kutt.calculate(X, Y[0], h, ny);
        X += h;
        F[1] = FPR(X, Y[1]);
        for (int i = 0; i < ny; i++){
            DF[1][i] = F[1][i] - F[0][i];
        }
        OUT(X, Y[1]);

        Y[2] = Rungie_Kutt.calculate(X, Y[1], h, ny);
        X += h;
        F[2] = FPR(X, Y[2]);
        for (int i = 0; i < ny; i++){
            DF[2][i] = F[2][i] - F[1][i];
            D2F[2][i] = DF[2][i] - DF[1][i];
        }
        OUT(X, Y[2]);

        Y[3] = Rungie_Kutt.calculate(X, Y[2], h, ny);
        X += h;
        F[3] = FPR(X, Y[3]);
        for (int i = 0; i < ny; i++){
            DF[3][i] = F[3][i] - F[2][i];
            D2F[3][i] = DF[3][i] - DF[2][i];
            D3F[3][i] = D2F[3][i] - D2F[2][i];
        }
        OUT(X, Y[3]);

        for (int n = 4; n <= nx; n++){
            for (int i = 0; i < ny; i++) {
                Y[n][i] = Y[n - 1][i] + h * (F[n - 1][i] +
                        DF[n - 1][i] / 2 +
                        D2F[n - 1][i] * 5 / 12 +
                        D3F[n - 1][i]*3/8);
            }
            X += h;
            F[n] = FPR(X, Y[n]);
            for (int i = 0; i < ny; i++){
                DF[n][i] = F[n][i] - F[n - 1][i];
                D2F[n][i] = DF[n][i] - DF[n - 1][i];
                D3F[n][i] = D2F[n][i] - D2F[n - 1][i];
            }
            OUT(X,Y[n]);
        }
    }
}
