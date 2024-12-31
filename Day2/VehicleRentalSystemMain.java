import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleRentalSystemMain {
    public static void main(String[] args) {
        VehicleRentalSystem system = new VehicleRentalSystem();
        system.initializeVehicles();
        system.handleUserInput();
    }
}

interface VehicleOperations {
    void displayDetails();
    double calculateRentalCost(int rentalDays);
}

abstract class Vehicle implements VehicleOperations {
    protected String vehicleType;
    protected String numberPlate;
    protected double rentalPricePerDayInINR;

    public Vehicle(String vehicleType, String numberPlate, double rentalPricePerDayInINR) {
        this.vehicleType = vehicleType;
        this.numberPlate = numberPlate;
        this.rentalPricePerDayInINR = rentalPricePerDayInINR;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    @Override
    public abstract void displayDetails();
}

class Car extends Vehicle {
    private int seatingCapacity;

    public Car(String numberPlate, double rentalPricePerDayInINR, int seatingCapacity) {
        super("Car", numberPlate, rentalPricePerDayInINR);
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public void displayDetails() {
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println("Number Plate: " + numberPlate);
        System.out.println("Rental Price per Day: " + rentalPricePerDayInINR);
        System.out.println("Seating Capacity: " + seatingCapacity);
    }

    @Override
    public double calculateRentalCost(int rentalDays) {
        return rentalDays * rentalPricePerDayInINR;
    }
}

class Bike extends Vehicle {
    private boolean hasHelmet;

    public Bike(String numberPlate, double rentalPricePerDayInINR, boolean hasHelmet) {
        super("Bike", numberPlate, rentalPricePerDayInINR);
        this.hasHelmet = hasHelmet;
    }

    @Override
    public void displayDetails() {
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println("Number Plate: " + numberPlate);
        System.out.println("Rental Price per Day: " + rentalPricePerDayInINR);
        System.out.println("Helmet Included: " + (hasHelmet ? "Yes" : "No"));
    }

    @Override
    public double calculateRentalCost(int rentalDays) {
        return rentalDays * rentalPricePerDayInINR;
    }
}

class Truck extends Vehicle {
    private double loadCapacity;

    public Truck(String numberPlate, double rentalPricePerDayInINR, double loadCapacity) {
        super("Truck", numberPlate, rentalPricePerDayInINR);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public void displayDetails() {
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println("Number Plate: " + numberPlate);
        System.out.println("Rental Price per Day: " + rentalPricePerDayInINR);
        System.out.println("Load Capacity: " + loadCapacity + " tons");
    }

    @Override
    public double calculateRentalCost(int rentalDays) {
        return rentalDays * rentalPricePerDayInINR;
    }
}

class VehicleRentalSystem {
    private List<Vehicle> vehicles;

    public VehicleRentalSystem() {
        vehicles = new ArrayList<>();
    }

    public void initializeVehicles() {
        // Adding vehicles with rental price in INR
        vehicles.add(new Car("MH12AB1234", 1500.0, 5)); // Car rental price 1500 INR per day
        vehicles.add(new Bike("MH14XY5678", 500.0, true)); // Bike rental price 500 INR per day
        vehicles.add(new Truck("MH09KL9876", 3500.0, 15)); // Truck rental price 3500 INR per day
    }

    public void displayMenu() {
        System.out.println("\n--- Vehicle Rental Menu ---");
        System.out.println("1. Display All Vehicles");
        System.out.println("2. Rent a Vehicle");
        System.out.println("3. Exit");
    }

    public void handleUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;

            do {
                displayMenu();
                System.out.print("Enter your choice: ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number between 1 and 3.");
                    System.out.print("Enter your choice: ");
                    scanner.next(); 
                }
                choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        displayAllVehicles();
                        break;
                    case 2:
                        rentVehicle(scanner);
                        break;
                    case 3:
                        System.out.println("Exiting the Vehicle Rental System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 3);
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
        }
    }

    private void displayAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available.");
            return;
        }
        System.out.println("\n--- Available Vehicles ---");
        for (Vehicle vehicle : vehicles) {
            vehicle.displayDetails();
            System.out.println();
        }
    }

    private void rentVehicle(Scanner scanner) {
        System.out.print("Enter the vehicle type (Car/Bike/Truck) to rent: ");
        String vehicleType = scanner.nextLine().trim();
        
        Vehicle selectedVehicle = null;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                selectedVehicle = vehicle;
                break;
            }
        }

        if (selectedVehicle != null) {
            System.out.print("Enter the number of days to rent: ");
            int rentalDays = scanner.nextInt();
            double totalCostInINR = selectedVehicle.calculateRentalCost(rentalDays);
            System.out.println("Total rental cost for " + rentalDays + " day(s):  " + totalCostInINR);
        } else {
            System.out.println("Vehicle type not found.");
        }
    }
}
