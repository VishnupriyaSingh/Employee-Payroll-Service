package com.day15;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class EPService {
    public static void main(String[] args) {
        PayrollDBService service = PayrollDBService.getInstance();
        try {
            int newEmployeeId = service.addEmployee
                    (80,
                    "Anisha",
                    "F",
                    20000,
                    new Date(2023-12-12),
                    "0987654321",
                    "Address 80",
                    "IT",
                    1200,
                    7000,
                    700,
                    9000,
                    15000
            );
            List<Integer> departmentIds = Arrays.asList(1, 2);
            service.addEmployeeToDepartments(newEmployeeId, departmentIds);
            System.out.println("New Employee Added: " + newEmployeeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}