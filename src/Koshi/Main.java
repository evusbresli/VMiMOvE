package Koshi;

import java.util.Scanner;

public class Main {
    private static int ny;

    static double[] FPR(double x, double[] Y){
        double[] F = new double[ny];
        F[0] = Y[0] / x + Y[1] - Math.pow(Math.E, x);
        F[1] = (((2 * x) / Y[0]) + (Math.pow(Y[1], 2) / Math.pow(Math.E, x))) - 1;
//        F[0] = Y[0] * ((1 / (2 * x)) - 1);
        return F;
    }

    private static void OUT(double x, double[] Y){
        System.out.printf("x=%.4f  y1=%.4f  u1=%.4f  d1=%.4f  y2=%.4f  u2=%.4f  d2=%.4f\n",
                x, Y[0], 2 * x, (2 * x) - Y[0], Y[1], Math.pow(Math.E, x), Math.pow(Math.E, x) - Y[1]);
//        System.out.printf("x=%.4f  y1=%.4f  u1=%.4f  d1=%.4f\n",
//                x, Y[0], 2 * x, (2 * x) - Y[0]);
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int k; double de, d, z;

        System.out.println("\nВведите интервал значений x:");
        System.out.print("\ta:=");
        //double a = reader.nextDouble();
        double a = 1;
        System.out.print("\tb:=");
        //double b = reader.nextDouble();
        double b = 4;
        System.out.print("\nВведите количество шагов сетки: ");
        //int nx = reader.nextInt();
        int nx = 10;
        System.out.print("\nВведите количество уравнений: ");
        //ny = reader.nextInt();
        ny = 2;
        System.out.print("\nВведите максимально допустимое количество итераций: ");
        //int nit = reader.nextInt();
        int nit = 10000;
        System.out.print("\nВведите точность: \n");
        //double eps = reader.nextDouble();
        double eps = 0.0001;

        double h = (b - a) / nx;
        double X = a;

        double[] Y = new double[ny];
        Y[0] = a * 2; Y[1] = Math.pow(Math.E, a);
        //Y[0] = 2.70;
        OUT(X, Y);
        double[] Y1 = Rungie_Kutt.calculate(X, Y, h, ny);
        X += h;
        OUT(X, Y1);
        double[] Y2 = Rungie_Kutt.calculate(X, Y1, h, ny);
        X += h;
        OUT(X, Y2);
        double[] Y3 = Rungie_Kutt.calculate(X, Y2, h, ny);
        X += h;
        OUT(X, Y3);


//        double[] Fm = FPR(X, Y);
//
//        X += h / 2;
//
//        double[] Yp = new double[ny];
//        for (int i = 0; i < ny; i++){
//            Yp[i] = Y[i] + ((h / 2) * Fm[i]);
//        }
//
//        double[] Fp1 = FPR(X, Yp);
//
//        for (int i = 0; i < ny; i++){
//            Yp[i] = Y[i] + ((h / 2) * Fp1[i]);
//        }
//
//        double[] Fp2 = FPR(X, Yp);
//
//        for (int i = 0; i < ny; i++){
//            Yp[i] = Y[i] + (h * Fp2[i]);
//        }
//
//        X += h / 2;
//
//        double[] Fp = FPR(X, Yp);
//
//        for (int i = 0; i < ny; i++){
//            Yp[i] = Y[i] + h / 6 * (Fm[i] + (2 * Fp1[i]) + (2 * Fp2[i]) + Fp[i]);
//        }
//
//        OUT(X, Y);
//
//        Fp = FPR(X, Yp);
//
//        for (int n = 2; n <= nx; n++){
//            for (int i = 0; i < ny; i++){
//                Y[i] = Yp[i] + h * (1.5 * Fp[i] - 0.5 * Fm[i]);
//            }
//
//            X += h;
//            double[] F = FPR(X, Y);
//            k = 0;
//            do {
//                k++; de = 0;
//                for (int i = 0; i < ny; i++){
//                    z = Yp[i] + h * ((5 / 12 * F[i]) + (8 / 12 * Fp[i]) + (1 / 12 * Fm[i]));
//                    d = Math.abs(z - Y[i]);
//                    Y[i] = z;
//                    if (d > de)
//                        de = d;
//                }
//
//                F = FPR(X, Y);
//            } while (k <= nit || de >= eps);
//
//            Yp = Y;
//            Fm = Fp;
//            Fp = F;
//
//            OUT(X, Y);
//        }
    }
}
