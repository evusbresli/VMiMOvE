package eigenvalue_eigenvector;

public abstract class DiagMax {
    public static void main(String[] args) {
        double[][] A = {{5, 1, 2},
                {1, 4, 1},
                {2, 1, 3}};
        double[] result = find(A);

        System.out.println(result[0] + "; " + result[1] + "; " + result[2]);
    }

    static double[] find(double[][] A){
        double[] result = {A[0][1], 0, 1};
        double max = A[0][1];

        for (int i = 0; i < A[0].length; i++){
            for (int j = i + 1; j < A[0].length; j++){
               if( Math.abs(A[i][j]) > max){
                   max = A[i][j];
                   result[0] = max; result[1] = i; result[2] = j;
               }
            }
        }

        return result;
    }
}
