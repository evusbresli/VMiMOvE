package approximation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("Добро пожаловать! Данная программа аппроксимирует функцию:");
        System.out.println("\t(sin(x))^2 - x/5. Используется метод Ньютона.");

        System.out.println("\nВведите интервал для вычисления узлов интерполяции, а также количество точек.");
        System.out.print("\ta:=");
        double a = reader.nextInt();
        System.out.print("\tb:=");
        double b;
        b = reader.nextInt();
        System.out.print("\tm:=");
        int m = reader.nextInt();

        double X[] = calculateX(m, a, b);
        double Y[] = calculateY(X, m);

        System.out.println("Начальная таблица данных: ");
        System.out.println("---------------------");
        System.out.printf("|%1$4s |%2$5s |%3$5s |\n", "№", "X", "Y");
        for (int i = 0; i < m; i++){
            System.out.printf("|%1$5d |%2$5.2f |%3$5.2f |\n", i, X[i], Y[i]);
        }
        System.out.println("---------------------");

        double Xj[] = calculateXj(a, b);

        calculateApproximation(Xj, X, Y, m);

    }

    private static void calculateApproximation(double[] Xj, double[] X, double[] Y, int m){
        double differences[][] = DividedDifferences.calculate(X, Y);
        System.out.printf("|%1$10s | %2$10s | %3$10s |\n", "f(xj)", "ф(xj, c)", "d(xj)");
        for (double x : Xj) {
            System.out.printf("|%10.4f ", calculateY(x));

            double N = Y[0];
            double polinom = 1;

            for (int k = 0; k < m - 1; k++) {
                polinom *= x - X[k];
                N += polinom * differences[k][0];
            }
            System.out.printf("| %1$10.4f | %2$10.4f |\n", N, calculateY(x) - N);
        }
    }



    private static double calculateY(double x){
        return Math.pow(Math.sin(x), 2) - x / 5;
        //return Math.sin(x);
    }

    private static double[] calculateXj(double a, double b){
        double Xj[] = new double[20];
        for (int j = 0; j < 20; j++){
            Xj[j] = a + j * (b - a) / 20;
        }

        return Xj;
    }

    private static double[] calculateX(int m, double a, double b){
        double X[] = new double[m];
        for(int i = 0; i < m; i++)
            X[i] = a + i * (b - a) / (m - 1);

        return X;
    }

    private static double[] calculateY(double[] X, int m){
        double Y[] = new double[m];
        for (int i = 0; i < m; i++){
            Y[i] = calculateY(X[i]);
        }

        return Y;
    }
}

