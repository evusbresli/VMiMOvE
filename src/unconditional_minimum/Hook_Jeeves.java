package unconditional_minimum;

import java.util.Scanner;

public class Hook_Jeeves {
    private static void printDot(double[] x){
        System.out.print("X = [");
        for (int i = 0; i < x.length-1; i++){
            System.out.printf("%.6f; ", x[i]);
        }
        System.out.printf("%.6f]\n", x[x.length-1]);
        System.out.printf("f(X) = %.6f\n",  f(x));
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Введите кол-во переменных: ");
        int n = reader.nextInt();

        double[] X0 = new double[n];
        for (int i = 0; i < n; i++){
            System.out.print("\tВведите координату X" + (i + 1) + ": ");
            X0[i] = reader.nextDouble();
        }
        System.out.print("Базисная точка: "); printDot(X0);

        double[] d = new double[n];
        System.out.println("Введите вектор приращения:");
        for (int i = 0; i < n; i++){
            d[i] = reader.nextDouble();
        }
        System.out.print("Введите коэффициент уменьшения шага: ");
        double h = reader.nextDouble();
        System.out.print("Введите точность: ");
        double eps = reader.nextDouble();

        printDot(X0);
        printDot(calculate(X0, d, h, eps));
    }

    private static double searchByPattern(double[] A, double[] p) {
        double a = 0, b = 100, lam, nu, fLam, fNu; int n = 100;

        for (int k = 1; k < n; k++){
            lam = a + (Fibo(n - k - 1)/Fibo(n - k + 1)*(b - a));
            nu = a + (Fibo(n - k)/Fibo(n - k + 1)*(b - a));
            fLam = f(sum(A, multiply(p, lam)));
            fNu = f(sum(A, multiply(p, nu)));
            if (fLam < fNu){
                b = nu;
            } else if (fLam > fNu){
                a = lam;
            }
        }

        return (a + b)/2;
    }

    private static double f(double[] X){
        //return 8*Math.pow(X[0], 2) + 4*X[0]*X[1] + 5*Math.pow(X[1], 2);
        //return 100*Math.pow((X[1] + Math.pow(X[0], 2)), 2) + Math.pow((1 - X[0]), 2);

        double O = Math.atan(X[1] - X[0]);
        double r = Math.sqrt(Math.pow(X[0], 2) + Math.pow(X[1], 2));

        return 100*(Math.pow(X[2] - 10*O, 2) + Math.pow(r - 1, 2)) + Math.pow(X[2], 3);
    }

    public static boolean check(double[] D, double eps){
        for (double d : D){
            if (d >= eps)
                return false;
        }
        return true;
    }

    private static double[] exploringSearch(double[] X1, double[] d, double f1, int n){
        double[] Xt;
        double ft;

        for (int i = 0; i < n; i++){
            Xt = X1.clone();
            Xt[i] += d[i];
            ft = f(Xt);
            if (ft < f1){
                X1 = Xt;
                f1 = ft;
            } else {
                Xt = X1.clone();
                Xt[i] -= d[i];
                ft = f(Xt);
                if (ft < f1){
                    X1 = Xt;
                    f1 = ft;
                }
            }
        }

        return X1;
    }

    public static double[] multiply(double[] X, double c){
        double[] M = new double[X.length];
        for (int i = 0; i < X.length; i++){
            M[i] = X[i]*c;
        }
        return M;
    }

    public static double[] divide(double[] X, double c){
        double[] M = new double[X.length];
        for (int i = 0; i < X.length; i++){
            M[i] = X[i]/c;
        }
        return M;
    }

    public static double[] sum(double[] A, double[] B){
        double[] M = new double[A.length];
        for (int i = 0; i < A.length; i++){
            M[i] = A[i] + B[i];
        }
        return M;
    }

    public static double[] diff(double[] A, double[] B){
        double[] M = new double[A.length];
        for (int i = 0; i < A.length; i++){
            M[i] = A[i] - B[i];
        }
        return M;
    }

    public static double Fibo(int n){
        if (n == 0)
            return 1;
        double[] A = new double[n + 1];
        A[0] = 1;
        A[1] = 1;
        for (int i = 2; i <= n; i++)
            A[i] = A[i - 1] + A[i - 2];
        return A[n];
    }

    private static double[] calculate(double[] X0, double[] d, double h, double eps){
        double f0;
        double[] X1;
        double f1, ft;
        double[] p;
        double aMin;

        do {
            f0 = f(X0); X1 = X0.clone(); f1 = f0;
            do {
                X1 = exploringSearch(X1, d, f1, 3);
                ft = f(X1);
                if (ft == f1){
                    if (check(d, eps)){
                        return X0;
                    }
                    d = divide(d, h);
                }
            } while (ft == f1);

            p = diff(X1.clone(), X0);
            aMin = searchByPattern(X1, p);
            X0 = sum(X1, multiply(p, aMin));
        } while (true);
    }
}





















