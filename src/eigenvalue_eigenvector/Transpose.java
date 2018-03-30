package eigenvalue_eigenvector;

public abstract class Transpose {

    static double[][] perform(double[][] matrix) throws IllegalArgumentException {
        if(matrix.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        int rowLength = matrix[0].length;
        for (double[] ai:matrix) {
            if (rowLength != ai.length) {
                throw new IllegalArgumentException("Non-equal rows");
            }
        }

        double[][] tMatrix = new double[rowLength][];
        for (int i = 0; i < rowLength; i++) {
            tMatrix[i] = new double[matrix.length];
        }
        for (int i = 0; i < matrix.length; i++) {
            double[] tArr = matrix[i];
            for (int j = 0; j < rowLength; j++) {
                tMatrix[j][i] = tArr[j];
            }
        }
        return tMatrix;
    }

    public static void main(String[] args) {
        double[][] a = new double[4][3];
        int counter = 0;
        for (double[] ai:a) {
            for (int i = 0; i < ai.length; i++) {
                ai[i] = counter++;
            }
        }
        try {
            a = perform(a);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        for(double[] ai:a) {
            for (double aai:ai) {
                System.out.print(aai);
                System.out.print("; ");
            }
            System.out.println();
        }
    }
}
