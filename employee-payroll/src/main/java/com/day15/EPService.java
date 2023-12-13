package com.day15;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public class EPService {

    public static void main(String[] args) {
        PayrollDBService payrollDBService = PayrollDBService.getInstance();
        try {
            Date startDate = Date.valueOf("2023-01-01");
            Date endDate = Date.valueOf("2023-12-31");
            List<EmpPayroll> employees = payrollDBService.getEmployeesJoinedInRange(startDate, endDate);

            for (EmpPayroll employee : employees) {
                System.out.println("Employee: " + employee.getName() + ", Joined: " + employee.getStart());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}