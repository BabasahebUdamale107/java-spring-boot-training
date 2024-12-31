import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RemoverDuplicateFromArray {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int n = sc.nextInt();
        
        int[] arr = new int[n];
        
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        Set<Integer> uniqueElements = new HashSet<>();
        for (int i = 0; i < n; i++) {
            uniqueElements.add(arr[i]);
        }

        System.out.println("Array after removing duplicates: ");
        for (int element : uniqueElements) {
            System.out.print(element + " ");
        }

        sc.close();
    }
}
