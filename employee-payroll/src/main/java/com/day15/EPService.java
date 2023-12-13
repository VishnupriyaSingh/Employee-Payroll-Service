package com.day15;

import java.sql.SQLException;

public class EPService {

    public static void main(String[] args) {
        try {
            PayrollDBService payrollDBService = PayrollDBService.getInstance();
            EmpPayroll employee = payrollDBService.getEmployeeDataByName("Terissa");
            if (employee != null) {
                System.out.println("Found employee: " + employee.getName());
            } else {
                System.out.println("Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}