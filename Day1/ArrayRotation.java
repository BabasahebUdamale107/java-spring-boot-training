import java.util.Arrays;
import java.util.Scanner;

public class ArrayRotation {

    public static void rotateArray(int[] arr, int k, String direction) {
        int n = arr.length;
        k = k % n;

        if (direction.equalsIgnoreCase("right")) {
            reverse(arr, 0, n - 1);
            reverse(arr, 0, k - 1);
            reverse(arr, k, n - 1);
        } else if (direction.equalsIgnoreCase("left")) {
            reverse(arr, 0, n - 1);
            reverse(arr, 0, n - k - 1);
            reverse(arr, n - k, n - 1);
        } else {
            System.out.println("Invalid direction. Please choose 'right' or 'left'.");
        }
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int n = sc.nextInt();
        
        int[] arr = new int[n];
        
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        System.out.print("Enter the number of positions to rotate: ");
        int k = sc.nextInt();
        
        System.out.print("Enter the direction of rotation (right/left): ");
        String direction = sc.next();

        System.out.println("Original array: " + Arrays.toString(arr));
        rotateArray(arr, k, direction);
        System.out.println("Array after " + k + " rotations to the " + direction + ": " + Arrays.toString(arr));
        
        sc.close();
    }
}
