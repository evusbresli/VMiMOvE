package Koshi;

public class Rungie_Kutt {

    public static double[] calculate(double X, double[] Y, double h, int ny){
        double[] Kn1 = Main.FPR(X, Y);
        double[] Yp = new double[ny];
        for (int i = 0; i < ny; i++){
            Yp[i] = Y[i] + h/2*Kn1[i];
        }
        double[] Kn2 = Main.FPR(X + h/2, Yp);
        for (int i = 0; i < ny; i++){
            Yp[i] = Y[i] + h/2*Kn2[i];
        }
        double[] Kn3 = Main.FPR(X + h/2, Yp);
        for (int i = 0; i < ny; i++){
            Yp[i] = Y[i] + h*Kn3[i];
        }
        double[] Kn4 = Main.FPR(X + h, Yp);
        for (int i = 0; i < ny; i++){
            Yp[i] = (Kn1[i] + 2*Kn2[i] + 2*Kn3[i] + Kn4[i])/6;
        }
        double[] Yn = new double[ny];
        for (int i = 0; i < ny; i++){
            Yn[i] = Y[i] + h* Yp[i];
        }

        return Yn;
    }
}
