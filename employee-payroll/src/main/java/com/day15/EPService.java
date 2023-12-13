package com.day15;

import java.sql.SQLException;
import java.util.List;

public class EPService {
    public static void main(String[] args) {
        PayrollDBService payrollDBService = new PayrollDBService();
        try {
            boolean isRemoved = payrollDBService.removeEmployee(1);
            if (isRemoved) {
                System.out.println("Employee removed successfully.");
            } else {
                System.out.println("Failed to remove employee.");
            }

            List<EmpPayroll> activeEmployees = payrollDBService.getActiveEmployeePayrollData();
            for (EmpPayroll employee : activeEmployees) {
                System.out.println(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}