import java.util.Scanner;

public class CircleCalculations {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the radius of the circle (in meters): ");
        double radius = sc.nextDouble();

        double area = Math.PI * Math.pow(radius, 2);
        double circumference = 2 * Math.PI * radius;

        System.out.println("Area of the circle: " + area + " square meters");
        System.out.println("Circumference of the circle: " + circumference + " meters");

        sc.close();
    }
}
