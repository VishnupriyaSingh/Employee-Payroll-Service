package com.day15;

import java.sql.SQLException;
import java.sql.Date;

public class EPService {
    public static void main(String[] args) {
        PayrollDBService payrollDBService = PayrollDBService.getInstance();
        try {
            EmpPayroll newEmployee = payrollDBService.addEmployeeToPayroll(78, "Arnav", 12000, new Date(2023-12-12), "M", "1234567890", "Address 78", "QA", 5000, 1200, 7000, 700, 9000);
            System.out.println("New Employee Added: " + newEmployee.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}