package com.day15;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeePayrollService service = new EmployeePayrollService();

        service.addEmployee(new EmployeePayroll(1, "Alice", 30000));
        service.addEmployee(new EmployeePayroll(2, "Bob", 40000));

        String filePath = "employees.txt";
        service.writeEmployeePayrollDataToFile(filePath);

        int entries = service.countEntriesInFile(filePath);
        System.out.println("Number of entries in file: " + entries);

        service.printEmployeePayrollsFromFile(filePath);

        List<EmployeePayroll> payrollList = service.readEmployeePayrollDataFromFile(filePath);
        for (EmployeePayroll employee : payrollList) {
            System.out.println(employee);
        }
    }
}