package derivative_and_inegral;

import approximation.DividedDifferences;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Добро пожаловать! Данная программа вычисляет интеграл и производные от  функции:");
        System.out.println("\t(sin(x))^2 - x/5. Интеграл вычисляется методом Симпсона.");

        System.out.println("\nВведите интервал значений x:");
        System.out.print("\ta:=");
        double a = reader.nextInt();
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

    static double[] calculateYj(double[] X){
        double Y[] = new double[X.length];
        for (int j = 0; j < X.length; j++){
            Y[j] = calculateY(X[j]);
        }

        return Y;
    }

    static double calculateY(double x){
        return Math.pow(Math.sin(x), 2) - x / 5;

        //return Math.pow(x, 2);

        //return Math.pow(x, 3);
    }

    private static void print(double X[], double Y[]){
        double YFirstDerivativeAcc[] = YFirstDerivativeAcc(X);
        double YSecondDerivativeAcc[] = YSecondDerivativeAcc(X);
        System.out.printf("+-----+---------+----------+----------+---------+----------+----------+---------+\n");
        System.out.printf("|  X  |    Y    | F`(X)точн| F`(X)приб|  погр.1 | F``(X)точ| F``(X)при|  погр.2 |\n");
        System.out.printf("=================================================================================\n");
        for (int j = 0; j < X.length; j++){
            System.out.printf("|%4.1f | %7.4f | %8.4f | %8.4f | %7.4f | %8.4f | %8.4f | %7.4f |\n", X[j], Y[j],
                    YFirstDerivativeAcc[j], YFirstDerivativeApp(X[j]),
                    Math.abs(YFirstDerivativeAcc[j] - YFirstDerivativeApp(X[j])),
                    YSecondDerivativeAcc[j], YSecondDerivativeApp(X[j]),
                    Math.abs(YSecondDerivativeAcc[j] - YSecondDerivativeApp(X[j])));
        }
        System.out.printf("=================================================================================\n");
        System.out.printf("Определенный интеграл: %8.4f\n", calculateIntegral(X, Y));
    }

    private static double[] YFirstDerivativeAcc(double[] X){
        double Y[] = new double[X.length];
        for (int i = 0; i < X.length; i++){
            Y[i] = (2 * Math.sin(X[i]) * Math.cos(X[i])) - (1 / 5);

            //Y[i] = 2 * X[i];

            //Y[i] = 3 * Math.pow(X[i], 2);
        }
        return Y;
    }

    private static double[] YSecondDerivativeAcc(double[] X){
        double Y[] = new double[X.length];
        for (int i = 0; i < X.length; i++){
            Y[i] = (2 * Math.pow(Math.cos(X[i]), 2)) - (2 * Math.pow(Math.sin(X[i]), 2));

            //Y[i] = 2;

            //Y[i] = X[i] * 6;
        }
        return Y;
    }

    private static double YFirstDerivativeApp(double X){
        double a = X - 0.2, b = X + 0.2;
        double P[] = new double[3];

        for (int i = 0; i < 3; i++){
            P[i] = a + i * (b - a) /2;
        }
        double PY[] = calculateYj(P);
        double differences[][] = DividedDifferences.calculate(P,PY);

        return differences[0][0] + (((X - P[0]) + (X - P[1])) * differences[1][0]);
    }

    private static double YSecondDerivativeApp(double X){
        double a = X - 0.2, b = X + 0.2;
        double P[] = new double[3];

        for (int i = 0; i < 3; i++){
            P[i] = a + i * (b - a) /2;
        }
        double PY[] = calculateYj(P);
        double differences[][] = DividedDifferences.calculate(P,PY);

        return (2 * differences[1][0]);
    }

    private static double calculateIntegral(double[] X, double[] Y){
        int n = (X.length - 1) / 2;
        double h = (X[X.length - 1] - X[0]) / (2 * n);

        System.out.printf("Ssi = %.4f * (%.4f + %.4f + %.4f + %.4f)\n", h/3, Y[0], 4*sumDecr(Y, n), 2*sum(Y, n), Y[n*2]);

        return (h / 3) * (Y[0] + (4 * sumDecr(Y, n)) + (2 * sum(Y, n)) + Y[n * 2]);
    }

    private static double sumDecr(double[] Y, int n){
        double sum = 0;
        for (int i = 1; i <= n; i++){
            sum += Y[i * 2 - 1];
        }

        return sum;
    }

    private static double sum(double[] Y, int n){
        double sum = 0;
        for (int i = 1; i <= n - 1; i++){
            sum += Y[i * 2];
        }

        return sum;
    }
}
