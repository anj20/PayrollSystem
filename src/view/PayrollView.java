package src.view;

import src.controller.PayrollController;
import src.model.Employee;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PayrollView {
    private PayrollController controller;

    public PayrollView(PayrollController controller) {
        this.controller = controller;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nPayroll Processing System");
            System.out.println("1. Display total number of employees");
            System.out.println("2. Display month-wise details");
            System.out.println("3. Display monthly salary report");
            System.out.println("4. Display employee-wise financial report");
            System.out.println("5. Display monthly amount released");
            System.out.println("6. Display yearly financial report");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            controller.loadDataFromFile("../data/Employee_details.txt");
            scanner.nextLine();  // Consume newline
            switch (choice) {
                case 1:
                    displayTotalEmployees();
                    break;
                case 2:
                    displayMonthWiseDetails();
                    break;
                case 3:
                    displayMonthlySalaryReport();
                    break;
                case 4:
                    displayEmployeeFinancialReport();
                    break;
                case 5:
                    displayMonthlyAmountReleased();
                    break;
                case 6:
                    displayYearlyFinancialReport();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    // 1. Total number of employees in an organization
    public void displayTotalEmployees() {
        int totalEmployees = controller.getTotalEmployees();
        System.out.println("Total number of employees: " + totalEmployees);
    }

    // 2. Month-wise details
    public void displayMonthWiseDetails() {
        Map<YearMonth, List<Employee>> joinedByMonth = controller.getEmployeesJoinedByMonth();
        Map<YearMonth, List<Employee>> exitedByMonth = controller.getEmployeesExitedByMonth();

        System.out.println("Month-wise details:");
        for (YearMonth month : joinedByMonth.keySet()) {
            System.out.println("Month: " + month);
            System.out.println("Employees Joined:");
            for (Employee e : joinedByMonth.get(month)) {
                System.out.println("ID: " + e.getEmpID() + ", Designation: " + e.getDesignation() + ", Name: " + e.getEmpFName() + " " + e.getEmpLName());
            }
            System.out.println("Employees Exited:");
            if (exitedByMonth.containsKey(month)) {
                for (Employee e : exitedByMonth.get(month)) {
                    System.out.println("Name: " + e.getEmpFName() + " " + e.getEmpLName());
                }
            }
        }
    }

    // 3. Monthly salary report
    public void displayMonthlySalaryReport() {
        Map<YearMonth, Double> salaryReport = controller.getMonthlySalaryReport();
        System.out.println("Monthly Salary Report:");
        for (YearMonth month : salaryReport.keySet()) {
            double totalSalary = salaryReport.get(month);
            long totalEmployees = controller.getEmployeesJoinedByMonth().get(month).size();
            System.out.println("Month: " + month + ", Total Salary: " + totalSalary + ", Total Employees: " + totalEmployees);
        }
    }

    // 4. Employee-wise financial report
    public void displayEmployeeFinancialReport() {
        Map<String, Double> financialReport = controller.getEmployeeFinancialReport();
        System.out.println("Employee-wise Financial Report:");
        for (String empID : financialReport.keySet()) {
            double totalAmount = financialReport.get(empID);
            Employee employee = controller.getEmployeeById(empID);
            System.out.println("Employee ID: " + empID + ", Name: " + employee.getEmpFName() + " " + employee.getEmpLName() + ", Total Amount Paid: " + totalAmount);
        }
    }

    // 5. Monthly amount released
    public void displayMonthlyAmountReleased() {
        Map<YearMonth, Double> amountReleased = controller.getMonthlyAmountReleased();
        System.out.println("Monthly Amount Released:");
        for (YearMonth month : amountReleased.keySet()) {
            double totalAmount = amountReleased.get(month);
            long totalEmployees = controller.getEmployeesJoinedByMonth().get(month).size();
            System.out.println("Month: " + month + ", Total Amount: " + totalAmount + ", Total Employees: " + totalEmployees);
        }
    }

    // 6. Yearly financial report
    public void displayYearlyFinancialReport() {
        Map<Integer, List<Employee>> yearlyReport = controller.getYearlyFinancialReport();
        System.out.println("Yearly Financial Report:");
        for (Integer year : yearlyReport.keySet()) {
            System.out.println("Year: " + year);
            for (Employee e : yearlyReport.get(year)) {
                System.out.println("Event: " + e.getEvent() + ", Employee ID: " + e.getEmpID() + ", Event Date: " + e.getEventDate() + ", Event Value: " + e.getValue());
            }
        }
    }
}
