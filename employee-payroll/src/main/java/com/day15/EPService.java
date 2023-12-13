package com.day15;

import java.sql.SQLException;
import java.sql.Date;

public class EPService {
    public static void main(String[] args) {
        PayrollDBService payrollDBService = PayrollDBService.getInstance();
        try {
            EmpPayroll newEmployee = payrollDBService.addEmployeeWithPayrollDetails(79, "Rishab", 12000, new Date(2023-12-13), "M", "1234567890", "Address 79", "QA", 5000, 1200, 7000, 700, 9000);
            System.out.println("New Employee Added: " + newEmployee.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}