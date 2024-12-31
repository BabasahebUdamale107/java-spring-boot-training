import java.util.Scanner;

public class MatrixAddition {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        int rows = sc.nextInt();
        
        System.out.print("Enter the number of columns: ");
        int cols = sc.nextInt();
        
        int[][] matrix1 = new int[rows][cols];
        int[][] matrix2 = new int[rows][cols];
        
        System.out.println("Enter elements of the first matrix:");
        inputMatrix(matrix1, rows, cols, sc);
        
        System.out.println("Enter elements of the second matrix:");
        inputMatrix(matrix2, rows, cols, sc);

        int[][] result = addMatrices(matrix1, matrix2, rows, cols);

        System.out.println("Resultant Matrix after Addition:");
        displayMatrix(result, rows, cols);

        sc.close();
    }

    // Method to input matrix values
    public static void inputMatrix(int[][] matrix, int rows, int cols, Scanner sc) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
    }

    // Method to add two matrices
    public static int[][] addMatrices(int[][] matrix1, int[][] matrix2, int rows, int cols) {
        int[][] result = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        
        return result;
    }

    // Method to display a matrix
    public static void displayMatrix(int[][] matrix, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
