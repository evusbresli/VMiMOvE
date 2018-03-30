package derivative_and_inegral;

import java.util.Scanner;

public class Derivative {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Добро пожаловать! Данная программа вычисляет интеграл и производные от  функции:");
        System.out.println("\t(sin(x))^2 - x/5. Интеграл вычисляется методом Симпсона.");

        System.out.println("\nВведите интервал значений x:");
        System.out.print("\ta:=");
        double a;
        a = reader.nextInt();
        System.out.print("\tb:=");
        double b = reader.nextInt();

        double X[] = calculateXj(a, b);
        double Y[] = calculateYj(X);

        print(X, Y);
    }

    private static double[] calculateXj(double a, double b){
        double X[] = new double[11];
        for (int j = 0; j < 11; j++){
            X[j] = a + j * (b - a) /10;
        }

        return X;
    }

    private static double[] calculateYj(double[] X){
        double Y[] = new double[X.length];
        for (int j = 0; j < X.length; j++){
            Y[j] = calculateY(X[j]);
        }

        return Y;
    }

    private static double calculateY(double x){
        return Math.pow(Math.sin(x), 2) - x / 5;
        //return Math.sin(x);
        //return Math.pow(x, 2);

        //return 4 * Math.pow(x, 3);
    }

    private static void print(double X[], double Y[]){
        double YFirstDerivativeAcc[] = YFirstDerivativeAcc(X);
        double YSecondDerivativeAcc[] = YSecondDerivativeAcc(X);
        System.out.print("+-----+---------+------------+------------+------------+------------+------------+------------+\n");
        System.out.print("|  X  |    Y    |  F`(X)точн |   погр.1   |  F`(X)приб |  F``(X)точ |  F``(X)при |   погр.2   |\n");
        System.out.print("===============================================================================================\n");
        for (int j = 0; j < X.length; j++){
            System.out.printf("|%4.1f | %7.4f | %10.7f | %10.7f | %10.7f | %10.7f | %10.7f | %10.7f |\n", X[j], Y[j],
                    YFirstDerivativeAcc[j], YFirstDerivativeApp(X[j]),
                    Math.abs(YFirstDerivativeAcc[j] - YFirstDerivativeApp(X[j])),
                    YSecondDerivativeAcc[j], YSecondDerivativeApp(X[j]),
                    Math.abs(YSecondDerivativeAcc[j] - YSecondDerivativeApp(X[j])));
        }
        System.out.print("===============================================================================================\n");
        //System.out.printf("Определенный интеграл: %8.4f\n", calculateIntegral(X, Y));
    }

    private static double[] YFirstDerivativeAcc(double[] X){
        double Y[] = new double[X.length];
        for (int i = 0; i < X.length; i++){
            Y[i] = (2 * Math.sin(X[i]) * Math.cos(X[i])) - (1 / 5) - 0.2;

            //Y[i] = 2 * X[i];
            //Y[i] = Math.cos(X[i]);

            //Y[i] = 3 * Math.pow(X[i], 2);
        }
        return Y;
    }

    private static double[] YSecondDerivativeAcc(double[] X){
        double Y[] = new double[X.length];
        for (int i = 0; i < X.length; i++){
            Y[i] = (2 * Math.pow(Math.cos(X[i]), 2)) - (2 * Math.pow(Math.sin(X[i]), 2));

            //Y[i] = 2;
            //Y[i] = -Math.cos(X[i]);

            //Y[i] = X[i] * 6;
        }
        return Y;
    }

    private static double YFirstDerivativeApp(double X){
        double h = 0.001;

        return (calculateY(X + h) - calculateY(X - h)) / (2 * h);
    }

    private static double YSecondDerivativeApp(double X){
        double h = 0.001;

        return ((calculateY(X + h) - (2 * calculateY(X))) + calculateY(X - h)) / Math.pow(h, 2);
    }
}
