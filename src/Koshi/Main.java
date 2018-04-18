package Koshi;

import java.util.Scanner;

public class Main {
    private static int ny;

    private static double[] FPR(double x, double[] Y){
        double[] F = new double[ny];
        F[0] = Y[0] / x + Y[1] - Math.pow(Math.E, x);
        F[1] = (((2 * x) / Y[0]) + (Math.pow(Y[1], 2) / Math.pow(Math.E, x))) - 1;

        return F;
    }

    private static void OUT(double x, double[] Y){
        System.out.printf("x=%.4f  y1=%.4f  u1=%.4f  d1=%.4f  y2=%.4f  u2=%.4f  d2=%.4f\n",
                x, Y[0], 2 * x, (2 * x) - Y[0], Y[1], Math.pow(Math.E, x), Math.pow(Math.E, x) - Y[1]);
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
        System.out.print("\nВведите точность: ");
        //double eps = reader.nextDouble();
        double eps = 0.0001;

        double h = (b - a) / nx;
        double x = a;

        double[] Y = new double[ny];
        Y[0] = a * 2; Y[1] = Math.pow(Math.E, a);
        OUT(x, Y);

        double[] Fm = FPR(x, Y);

        double[] Yp = new double[ny];
        for (int i = 0; i < ny; i++){
            Yp[i] = Y[i] + ((h / 2) * Fm[i]);
        }

        x += h / 2;

        double[] Fp1 = FPR(x, Yp);

        for (int i = 0; i < ny; i++){
            Yp[i] = Y[i] + ((h / 2) * Fp1[i]);
        }

        double[] Fp2 = FPR(x, Yp);

        for (int i = 0; i < ny; i++){
            Yp[i] = Y[i] + (h * Fp2[i]);
        }

        x += h / 2;

        double[] Fp = FPR(x, Yp);

        for (int i = 0; i < ny; i++){
            Yp[i] = Y[i] + ((h / 6) * (Fm[i] + (2 * Fp1[i]) + (2 * Fp2[i]) + Fp[i]));
        }

        OUT(x, Y);

        Fp = FPR(x, Yp);

        for (int n = 2; n <= nx; n++){
            for (int i = 0; i < ny; i++){
                Y[i] = Yp[i] + ((h / 2) * ((3 * Fp[i]) - Fm[i]));
            }

            x += h;
            double[] F = FPR(x, Y);
            k = 0;
            do {
                k++; de = 0;
                for (int i = 0; i < ny; i++){
                    z = Yp[i] + ((h / 12) * ((5 * F[i]) + (8 * Fp[i]) + Fm[i]));
                    d = Math.abs(z - Y[i]);
                    Y[i] = z;
                    if (d > de)
                        de = d;
                }

                F = FPR(x, Y);
            } while (k <= nit || de >= eps);

            Yp = Y;
            Fm = Fp;
            Fp = F;

            OUT(x, Y);
        }
    }
}
