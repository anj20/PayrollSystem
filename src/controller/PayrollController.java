package src.controller;

import src.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class PayrollController {
    private List<Employee> employees = new ArrayList<>();

    // Method to load data from a file
    public void loadDataFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Employee employee = Employee.parseEmployeeData(line);
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 1. Total number of employees in an organization
    public int getTotalEmployees() {
        return (int) employees.stream()
                .filter(e -> e.getEvent().equals("ONBOARD"))
                .count();
    }

    // 2. Month-wise details
    // a. Total number of employees joined with details
    public Map<YearMonth, List<Employee>> getEmployeesJoinedByMonth() {
        return employees.stream()
                .filter(e -> e.getEvent().equals("ONBOARD"))
                .collect(Collectors.groupingBy(e -> YearMonth.from(e.getEventDate())));
    }

    // b. Total number of employees exited with details
    public Map<YearMonth, List<Employee>> getEmployeesExitedByMonth() {
        return employees.stream()
                .filter(e -> e.getEvent().equals("EXIT"))
                .collect(Collectors.groupingBy(e -> YearMonth.from(e.getEventDate())));
    }

    // 3. Monthly salary report
    public Map<YearMonth, Double> getMonthlySalaryReport() {
        return employees.stream()
                .filter(e -> e.getEvent().equals("SALARY"))
                .collect(Collectors.groupingBy(
                        e -> YearMonth.from(e.getEventDate()),
                        Collectors.summingDouble(e -> (double) e.getValue())
                ));
    }

    // 4. Employee-wise financial report
    public Map<String, Double> getEmployeeFinancialReport() {
        return employees.stream()
                .filter(e -> Arrays.asList("SALARY", "BONUS", "REIMBURSEMENT").contains(e.getEvent()))
                .collect(Collectors.groupingBy(
                        Employee::getEmpID,
                        Collectors.summingDouble(e -> (double) e.getValue())
                ));
    }

    // 5. Monthly amount released
    public Map<YearMonth, Double> getMonthlyAmountReleased() {
        return employees.stream()
                .filter(e -> Arrays.asList("SALARY", "BONUS", "REIMBURSEMENT").contains(e.getEvent()))
                .collect(Collectors.groupingBy(
                        e -> YearMonth.from(e.getEventDate()),
                        Collectors.summingDouble(e -> (double) e.getValue())
                ));
    }

    // 6. Yearly financial report
    public Map<Integer, List<Employee>> getYearlyFinancialReport() {
        return employees.stream()
                .filter(e -> Arrays.asList("SALARY", "BONUS", "REIMBURSEMENT").contains(e.getEvent()))
                .collect(Collectors.groupingBy(e -> e.getEventDate().getYear()));
    }
    // Method to get an employee by their ID
    public Employee getEmployeeById(String empID) {
        return employees.stream()
                .filter(e -> e.getEmpID().equals(empID))
                .findFirst()
                .orElse(null);
    }
}
