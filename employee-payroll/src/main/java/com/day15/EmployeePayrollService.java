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

}