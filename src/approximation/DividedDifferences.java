package approximation;

class DividedDifferences {

    static double[][] calculate(double[] X, double[] Y){
        int n = X.length - 1;
        double differences[][] = new double[n][n];

        for (int i = 0; i < n; i++){
            differences[0][i] = (Y[i] - Y[i + 1]) / (X[i] - X[i + 1]);
        }

        int k = 1;
        for (int i = 1; i < n; i++){
            for (int j = 0; j < n - k; j++){
                differences[i][j] = (differences[i - 1][j] - differences[i - 1][j + 1]) / (X[i + j - k] - X[i + j + 1]);
            }
            k++;
        }

        print(differences, n);

        return differences;
    }

    private static void print(double differences[][], int n){
        System.out.println("Таблица разделенных разностей: ");
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%9.4f", differences[i][j]);
            }
            System.out.println();
        }
        System.out.println("=============================================");
    }
}
