import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollSystemMain {
    public static void main(String[] args) {
        EmployeePayrollSystem system = new EmployeePayrollSystem();
        system.handleUserInput();
    }
}

abstract class Employee {
    protected String name;
    protected String id;
    protected String designation;
    protected double basicSalary;

    public Employee(String name, String id, String designation, double basicSalary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.basicSalary = basicSalary;
    }

    public abstract double calculateSalary();

    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Designation: " + designation);
        System.out.println("Basic Salary: " + basicSalary);
    }
}

class FullTimeEmployee extends Employee {
    private double hra;
    private double da;

    public FullTimeEmployee(String name, String id, String designation, double basicSalary, double hra, double da) {
        super(name, id, designation, basicSalary);
        this.hra = hra;
        this.da = da;
    }

    @Override
    public double calculateSalary() {
        return basicSalary + hra + da;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("HRA: " + hra);
        System.out.println("DA: " + da);
    }
}

class PartTimeEmployee extends Employee {
    private double hourlyWage;
    private int hoursWorked;

    public PartTimeEmployee(String name, String id, String designation, double basicSalary, double hourlyWage, int hoursWorked) {
        super(name, id, designation, basicSalary);
        this.hourlyWage = hourlyWage;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyWage * hoursWorked;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Hourly Wage: " + hourlyWage);
        System.out.println("Hours Worked: " + hoursWorked);
    }
}

class EmployeePayrollSystem {
    private List<Employee> employees;

    public EmployeePayrollSystem() {
        employees = new ArrayList<>();
        initializeEmployees(); 
    }

    public void initializeEmployees() {
        employees.add(new FullTimeEmployee("Ashok Deshmukh", "FT002", "S-Dev", 55000, 9000, 7000));
        employees.add(new PartTimeEmployee("Amit Patil", "PT002", "S-Test", 18000, 250, 70));       
    }

    public void displayMenu() {
        System.out.println("\n--- Employee Payroll System Menu ---");
        System.out.println("1. Display All Employees");
        System.out.println("2. Calculate Salary for an Employee");
        System.out.println("3. Add Full-Time Employee");
        System.out.println("4. Add Part-Time Employee");
        System.out.println("5. Exit");
    }

    public void handleUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;

            do {
                displayMenu();
                System.out.print("Enter your choice: ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    System.out.print("Enter your choice: ");
                    scanner.next();
                }
                choice = scanner.nextInt();
                scanner.nextLine();  

                switch (choice) {
                    case 1:
                        displayAllEmployees();
                        break;
                    case 2:
                        System.out.print("Enter the ID of the employee to calculate salary: ");
                        String employeeId = scanner.nextLine();
                        calculateEmployeeSalary(employeeId);
                        break;
                    case 3:
                        addFullTimeEmployee(scanner);
                        break;
                    case 4:
                        addPartTimeEmployee(scanner);
                        break;
                    case 5:
                        System.out.println("Exiting the Employee Payroll System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 5);
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
        }
    }

    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees in the system.");
            return;
        }
        System.out.println("\n--- Employee Details ---");
        for (Employee employee : employees) {
            employee.displayDetails();
            System.out.println();
        }
    }

    public void calculateEmployeeSalary(String employeeId) {
        for (Employee employee : employees) {
            if (employee.id.equals(employeeId)) {
                System.out.println("Salary for " + employee.name + ": " + employee.calculateSalary());
                return;
            }
        }
        System.out.println("Employee with ID " + employeeId + " not found.");
    }

    public void addFullTimeEmployee(Scanner scanner) {
        System.out.println("\n--- Add Full-Time Employee ---");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter designation: ");
        String designation = scanner.nextLine();
        System.out.print("Enter basic salary: ");
        double basicSalary = scanner.nextDouble();
        System.out.print("Enter HRA: ");
        double hra = scanner.nextDouble();
        System.out.print("Enter DA: ");
        double da = scanner.nextDouble();
        scanner.nextLine(); 

        FullTimeEmployee employee = new FullTimeEmployee(name, id, designation, basicSalary, hra, da);
        employees.add(employee);
        System.out.println("Full-time employee " + name + " added successfully.");
    }

    public void addPartTimeEmployee(Scanner scanner) {
        System.out.println("\n--- Add Part-Time Employee ---");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter designation: ");
        String designation = scanner.nextLine();
        System.out.print("Enter basic salary: ");
        double basicSalary = scanner.nextDouble();
        System.out.print("Enter hourly wage: ");
        double hourlyWage = scanner.nextDouble();
        System.out.print("Enter hours worked: ");
        int hoursWorked = scanner.nextInt();
        scanner.nextLine(); 
        PartTimeEmployee employee = new PartTimeEmployee(name, id, designation, basicSalary, hourlyWage, hoursWorked);
        employees.add(employee);
        System.out.println("Part-time employee " + name + " added successfully.");
    }
}
