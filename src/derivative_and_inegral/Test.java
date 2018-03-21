package derivative_and_inegral;

import approximation.DividedDifferences;

import static derivative_and_inegral.Main.calculateY;
import static derivative_and_inegral.Main.calculateYj;

public class Test {
    public static void main(String[] args) {
        double x = -100;


        double a = x - 0.2, b = x + 0.2;
        double P[] = new double[3];

        for (int i = 0; i < 3; i++){
            P[i] = a + i * (b - a) /2;
        }
        double PY[] = calculateYj(P);
        double h = (b - a) / (2 * 2);
        double differences[][] = DividedDifferences.calculate(P,PY);
        double xt = -99.93333333333334;
        for (double p : P){
            System.out.println(p);
        }

        double var1 = differences[0][0]/h + differences[1][0]/(2 * h) ;
        double var2 = differences[0][0] + (((xt - P[0]) + (xt - P[1])) * differences[1][0]) +
                (((xt - P[0])*(xt - P[1]) + (xt - P[0])*(xt - P[2]) + (xt - P[1])*(xt - P[2]))* differences[2][0]);
        double var3 = (2 * Math.sin(xt) * Math.cos(xt)) - (1 / 5);

        System.out.println(var1);
        System.out.println(var2);
        System.out.println(var3);


    }
}
