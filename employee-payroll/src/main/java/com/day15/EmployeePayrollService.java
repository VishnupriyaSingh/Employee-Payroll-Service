package com.day15;

import java.util.Scanner;

public class EmployeePayrollService {

    private EmployeePayroll readEmployeePayrollData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter Employee Name: ");
        String name = scanner.next();
        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();

        return new EmployeePayroll(id, name, salary);
    }

    private void writeEmployeePayrollData(EmployeePayroll employeePayroll) {
        System.out.println("Employee Payroll Data: " + employeePayroll);
    }

    public static void main(String[] args) {
        EmployeePayrollService service = new EmployeePayrollService();
        EmployeePayroll employeePayroll = service.readEmployeePayrollData();
        service.writeEmployeePayrollData(employeePayroll);
    }
}