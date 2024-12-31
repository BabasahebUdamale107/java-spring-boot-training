import java.util.Scanner;

public class ArraySumAndAverage {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int n = sc.nextInt();
        
        int[] arr = new int[n];
        
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        double average = (double) sum / n;
        
        System.out.println("Sum of the array elements: " + sum);
        System.out.println("Average of the array elements: " + average);
        
        sc.close();
    }
}
