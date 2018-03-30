package eigenvalue_eigenvector;

public abstract class MatrixMultiplection {

    static double[][] calculate(double[][] mA, double[][] mB){
        int m = mA.length;
        int n = mB[0].length;
        int o = mB.length;
        double[][] res = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < o; k++) {
                    res[i][j] += mA[i][k] * mB[k][j];
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        double[][] mA =
                {{-1, -6},
                        {2, 6}};

        double[][] mB =
                {{2},
                        {-1}};



        double[][] res = calculate(mA, mB);

        for (double[] re : res) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.format("%6.3f ", re[j]);
            }
            System.out.println();
        }
    }
}
