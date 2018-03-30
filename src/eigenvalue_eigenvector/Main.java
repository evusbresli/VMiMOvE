package eigenvalue_eigenvector;

public class Main {
    public static final double eps = 0.001;

    public static void main(String[] args) {
        double[][] A = {{5, 1, 2},
                {1, 4, 1},
                {2, 1, 3}};

        double[][] H = {{0.85065, 0, -0.52573},
                {0, 1, 0},
                {0.52573, 0, 0.85065}};

        double[][] result = Transpose.perform(H);
        result = MatrixMultiplection.calculate(result, A);
        result = MatrixMultiplection.calculate(result, H);

        for (double[] row : result){
            for (double col : row){
                System.out.print(col + "; ");
            }
            System.out.println();
        }
    }
}
