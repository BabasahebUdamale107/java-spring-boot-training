import java.util.Scanner;

public class DailyStepTrackerMain {

    public static void main(String[] args) {
        // Arrays for month names and days in each month (adjust for leap years in February)
        String[] months = {
            "January", "February", "March", "April", "May", "June", 
            "July", "August", "September", "October", "November", "December"
        };
        
        // Days in each month (non-leap year). February has 28 days. Leap year will be handled later.
        int[] daysInMonth = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };

        Scanner scanner = new Scanner(System.in);

        // Ask if it is a leap year
        System.out.print("Is it a leap year? (true/false): ");
        boolean isLeapYear = scanner.nextBoolean();

        // Adjust for leap year (February has 29 days)
        if (isLeapYear) {
            daysInMonth[1] = 29; // February has 29 days in a leap year
        }

        // Loop through each month
        for (int i = 0; i < 12; i++) {
            int daysInCurrentMonth = daysInMonth[i];
            int[] steps = new int[daysInCurrentMonth];
            recordStepsForMonth(steps, months[i], daysInCurrentMonth, scanner);

            int totalSteps = calculateTotalSteps(steps);
            double averageSteps = calculateAverageSteps(totalSteps, daysInCurrentMonth);
            int bestDayIndex = findBestDay(steps);

            displayMonthlySummary(steps, months[i], totalSteps, averageSteps, bestDayIndex);
        }
    }

    // Method to record steps for each day of a month
    public static void recordStepsForMonth(int[] steps, String month, int daysInMonth, Scanner scanner) {
        System.out.println("\nRecording steps for " + month + ":");
        for (int i = 0; i < daysInMonth; i++) {
            System.out.print("Enter the steps for day " + (i + 1) + ": ");
            steps[i] = scanner.nextInt();
        }
    }

    // Method to calculate total steps for the month
    public static int calculateTotalSteps(int[] steps) {
        int total = 0;
        for (int step : steps) {
            total += step;
        }
        return total;
    }

    // Method to calculate average steps for the month
    public static double calculateAverageSteps(int totalSteps, int daysInMonth) {
        return totalSteps / (double) daysInMonth;
    }

    // Method to find the best day (with the maximum steps) of the month
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

    // Method to display monthly summary
    public static void displayMonthlySummary(int[] steps, String month, int totalSteps, double averageSteps, int bestDayIndex) {
        System.out.println("\n--- " + month + " Step Tracker Summary ---");
        System.out.println("Total steps for the month: " + totalSteps);
        System.out.printf("Average steps per day: %.2f%n", averageSteps);
        System.out.println("Best day: Day " + (bestDayIndex + 1) + " with " + steps[bestDayIndex] + " steps.");
    }
}
