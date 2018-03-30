package derivative_and_inegral;

import java.util.Scanner;

public class Integral {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        System.out.println("\nВведите интервал значений x:");
        System.out.print("\ta:=");
        double a = reader.nextInt();
        System.out.print("\tb:=");
        double b = reader.nextInt();
        System.out.println("Введите количество отрезков разбиения:");
        System.out.print("\tn:=");
        int n = reader.nextInt();

        double h = (b - a) / (2 * n);

        double X[] = calculateX(a, h, n);
        double Y[] = calculateY(X);

        print(X, Y);

        calculateIntegral(Y, h, n);

    }

    private static double[] calculateX(double a, double h, int n){
        double X[] = new double[2 * n + 1];
        for (int i = 0; i <= 2 * n; i++){
            X[i] = a + i * h;
        }

        return X;
    }

    private static double[] calculateY(double[] X){
        double Y[] = new double[X.length];
        for (int i = 0; i < X.length; i++){
            //Y[i] = Math.pow(Math.sin(X[i]), 2) - X[i] / 5;
            //Y[i] = X[i] / (Math.pow(X[i], 4) + 4);
            Y[i] = 4 * Math.pow(X[i], 3);
        }

        return Y;
    }

    private static void print(double[] X, double[] Y){
        System.out.print("+------------+------------+\n");
        System.out.print("|     Xi     |     Yi     |\n");
        System.out.print("===========================\n");
        for (int i = 0; i < X.length; i++){
            System.out.printf("| %10.2f | %10.5f |\n", X[i], Y[i]);
        }
        System.out.print("===========================\n");
    }

    private static void calculateIntegral(double[] Y, double h, int n){
        double Ssi = (h / 3) * (Y[0]  + (4 * sumDecr(Y, n)) + (2 * sum(Y, n)) + Y[2 * n]);

        System.out.println("Определенный интеграл: " + Ssi);
    }

    private static double sumDecr(double[] Y, int n){
        double sum = 0;

        for (int i = 1; i <= n; i++){
            sum += Y[2 * i - 1];
        }

        return sum;
    }

    private static double sum(double[] Y, int n){
        double sum = 0;

        for (int i = 1; i <= n -1; i++){
            sum += Y[2 * i];
        }

        return sum;
    }
}