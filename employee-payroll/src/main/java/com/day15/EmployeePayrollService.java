package com.day15;

import java.util.Scanner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollService {

    private List<EmployeePayroll> employeePayrollList;

    public EmployeePayrollService() {
        this.employeePayrollList = new ArrayList<>();
    }

    public void addEmployee(EmployeePayroll employee) {
        employeePayrollList.add(employee);
    }

    public void writeEmployeePayrollDataToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (EmployeePayroll employee : employeePayrollList) {
                writer.write(employee.toString() + "\n");
            }
            System.out.println("Employee Payroll Data written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int countEntriesInFile(String filePath) {
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) lineCount++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineCount;
    }

    public void printEmployeePayrollsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<EmployeePayroll> readEmployeePayrollDataFromFile(String filePath) {
        List<EmployeePayroll> payrollList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                EmployeePayroll employee = parseEmployeePayroll(line);
                if (employee != null) {
                    payrollList.add(employee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payrollList;
    }

    private EmployeePayroll parseEmployeePayroll(String data) {
        try {
            String[] parts = data.split(",\\s*");
            // Assuming the format is like "EmployeePayroll{id=1, name='Alice', salary=30000.0}"
            int id = Integer.parseInt(parts[0].split("=")[1]);
            String name = parts[1].split("'")[1]; // Splitting by single quote to extract the name
            double salary = Double.parseDouble(parts[2].split("=")[1].replaceAll("[^\\d.]", "")); // Removing any non-numeric characters from the salary

            return new EmployeePayroll(id, name, salary);
        } catch (Exception e) {
            System.err.println("Error parsing payroll data: " + data);
            return null;
        }
    }
}