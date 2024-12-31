import java.util.Scanner;

public class DailyStepTracker {

    public static void main(String[] args) {
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int[] steps = new int[7];
        recordSteps(steps, daysOfWeek);
        int totalSteps = calculateTotalSteps(steps);
        double averageSteps = calculateAverageSteps(totalSteps, 7);
        int bestDayIndex = findBestDay(steps);

        displaySummary(steps, daysOfWeek, totalSteps, averageSteps, bestDayIndex);
    }

 
    public static void recordSteps(int[] steps, String[] daysOfWeek) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Daily Step Tracker!");
        for (int i = 0; i < steps.length; i++) {
            System.out.print("Enter the steps taken on " + daysOfWeek[i] + ": ");
            steps[i] = scanner.nextInt();
        }
    }


    public static int calculateTotalSteps(int[] steps) {
        int total = 0;
        for (int step : steps) {
            total += step;
        }
        return total;
    }


    public static double calculateAverageSteps(int totalSteps, int days) {
        return totalSteps / (double) days;
    }


    public static int findBestDay(int[] steps) {
        int maxSteps = 0;
        int bestDayIndex = 0;
        for (int i = 0; i < steps.length; i++) {
            if (steps[i] > maxSteps) {
                maxSteps = steps[i];
                bestDayIndex = i;
            }
        }
        return bestDayIndex;
    }

    public static void displaySummary(int[] steps, String[] daysOfWeek, int totalSteps, double averageSteps, int bestDayIndex) {
        System.out.println("\n--- Step Tracker Summary ---");
        System.out.println("Total steps for the week: " + totalSteps);
        System.out.printf("Average steps per day: %.2f%n", averageSteps);
        System.out.println("Best day: " + daysOfWeek[bestDayIndex] + " with " + steps[bestDayIndex] + " steps.");
    }
}
