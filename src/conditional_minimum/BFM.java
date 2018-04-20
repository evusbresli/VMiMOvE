package conditional_minimum;
import static unconditional_minimum.Hook_Jeeves.*;

public class BFM {
    private static void printDot(double[] x){
        System.out.print("X = [");
        for (int i = 0; i < x.length-1; i++){
            System.out.printf("%.6f; ", x[i]);
        }
        System.out.printf("%.6f]\n", x[x.length-1]);
        System.out.printf("f(X) = %.10f\n",  f(x));
    }

    public static void main(String[] args) {
        double eps = 0.0000001;
        double[] X = {-1, 1};
        double[] X1;
        double[] d = {1, 1};
        double r = 1;
        double b = 2;
        double ch;
        double F;

        do {
            X = hook_jeeves(X, d, 4, eps, r);
            F = f(X);
            ch = r*a(X);
            if (ch <= eps){
                printDot(X);
                return;
            }
            r *= b;
        } while (true);

    }

    private static double f(double[] x){
        return Math.pow(x[0], 2) + Math.pow(x[1], 2);
        //return 100*Math.pow(x[1] + Math.pow(x[0], 2), 2) + Math.pow(1 - x[0], 2);
//        double O = Math.atan(x[1] - x[0]);
//        double r = Math.sqrt(Math.pow(x[0], 2) + Math.pow(x[1], 2));
//
//        return 100*(Math.pow(x[2] - 10*O, 2) + Math.pow(r - 1, 2)) + Math.pow(x[2], 3);
    }

    private static double a(double[] x){
        //return R(1 - x[0] - x[1]);
        return R(x[0]);
    }

    private static double R(double y){
        return Math.pow(Double.max(0,-y), 2);
    }

    private static double Q(double[] x, double r){
        return f(x) + r*a(x);
    }

    private static double[] hook_jeeves(double[] X0, double[] d, double h, double eps, double r){
        double f0;
        double[] X1;
        double f1, ft;
        double[] p;
        double aMin;

        do {

            f0 = Q(X0, r); X1 = X0.clone(); f1 = f0;
            do {
                X1 = exploringSearch(X1, d, f1, 2, r);
                ft = Q(X1,r);
                if (ft == f1){
                    if (check(d, eps)){
                        return X0;
                    }
                    d = divide(d, h);
                }
            } while (ft == f1);

            p = diff(X1.clone(), X0);
            aMin = searchByPattern(X1, p, r);
            X1 = sum(X1, multiply(p, aMin));
            X0 = X1;
        } while (true);
    }

    private static double[] exploringSearch(double[] X1, double[] d, double f1, int n, double r){
        double[] Xt;
        double ft;

        for (int i = 0; i < n; i++){
            Xt = X1.clone();
            Xt[i] += d[i];
            ft = Q(Xt, r);
            if (ft < f1){
                X1 = Xt;
                f1 = ft;
            } else {
                Xt = X1.clone();
                Xt[i] -= d[i];
                ft = Q(Xt, r);
                if (ft < f1){
                    X1 = Xt;
                    f1 = ft;
                }
            }
        }

        return X1;
    }

    private static double searchByPattern(double[] A, double[] p, double r) {
        double a = -1, b = 100, lam, nu, fLam, fNu; int n = 100;

        for (int k = 1; k < n; k++){
            lam = a + (Fibo(n - k - 1)/Fibo(n - k + 1)*(b - a));
            nu = a + (Fibo(n - k)/Fibo(n - k + 1)*(b - a));
            fLam = Q(sum(A, multiply(p, lam)), r);
            fNu = Q(sum(A, multiply(p, nu)), r);
            if (fLam < fNu){
                b = nu;
            } else if (fLam > fNu){
                a = lam;
            }
        }

        return (a + b)/2;
    }
}
