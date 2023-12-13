package com.day15;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EPService {

    public static void main(String[] args) {
        try {
            List<EmpPayroll> payrollList = getEmpPayrollData();
        } catch (PayrollServiceException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static List<EmpPayroll> getEmpPayrollData() throws PayrollServiceException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service";
        String username = "root";
        String password = "anubhav08";

        List<EmpPayroll> payrollList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "SELECT * FROM employee_payroll";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("Name");
                    double salary = resultSet.getDouble("Salary");
                    Date start = resultSet.getDate("Start");

                    EmpPayroll employee = new EmpPayroll();
                    employee.setId(id);
                    employee.setName(name);
                    employee.setSalary(salary);
                    employee.setStart(start);

                    payrollList.add(employee);

                    System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary + ", Start Date: " + start);
                }
            }
        } catch (SQLException e) {
            throw new PayrollServiceException("Failed to retrieve payroll data", e);
        }

        return payrollList;
    }
}