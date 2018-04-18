package nonlinear_equations;

import java.util.Scanner;

public class Main {
    private static final double h = 0.001;

    private static double f(double x){
       return Math.pow(Math.sin(x), 2) - (x / 5) - 1;
//        return ((0.1 * Math.pow(x, 3)) + Math.pow(x, 2)) - (10 * Math.sin(x)) - 8;
//        return x * x - 4;
    }

    private static double df(double x){
        return (f(x + h) - f(x - h)) / (2 * h);
    }

    private static double d2f(double x){
        return ((f(x + h) - (2 * f(x))) + f(x - h)) / Math.pow(h, 2);
    }

    private static double findRoot(double a, double b, double eps){
        double x0, xn;

        if (f(a) * d2f(a) > 0) x0 = a;
        else x0 = b;

        xn = x0 - f(x0) / df(x0);
        while (Math.abs(x0 - xn) > eps){
            x0 = xn;
            xn = x0 - f(x0) / df(x0);
        }

        return xn;
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int exit, i;
        double a, b, eps, h = 0.01;

        do {
            i = 0;
            System.out.println("\nВведите интервал значений x:");
            System.out.print("\ta:=");
            a = reader.nextDouble();
            System.out.print("\tb:=");
            b = reader.nextDouble();
            System.out.println("\nВведите точность измерений eps:");
            eps = reader.nextDouble();

            if (a > b){
                double temp = a;
                a = b;
                b = temp;
            }

            while (a < b){
                if (f(a) * f(a + h) <= 0)
                    System.out.println("Корень №" + ++i + ": " + findRoot(a, a + h, eps));
                a = a + h;
            }

            System.out.print("Выход? ");
            exit = reader.nextInt();
        } while (exit != 1);
    }
}
