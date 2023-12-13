package com.day15;

import java.sql.*;

public class EPService {

    public static void main(String[] args) {
        try {
            updateEmployeeSalary("Terissa", 3000000.00);
            EmpPayroll updatedEmployee = getEmployeeDataByName("Terissa");
        } catch (PayrollServiceException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void updateEmployeeSalary(String name, double newSalary) throws PayrollServiceException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service";
        String username = "root";
        String password = "Priya@002";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String updateQuery = "UPDATE employee_payroll SET Salary = ? WHERE Name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setDouble(1, newSalary);
                preparedStatement.setString(2, name);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 0) {
                    throw new PayrollServiceException("No rows affected. Employee not found.");
                }

                System.out.println("Salary updated for " + name);
            }
        } catch (SQLException e) {
            throw new PayrollServiceException("Failed to update salary in database", e);
        }
    }

    public static EmpPayroll getEmployeeDataByName(String name) throws PayrollServiceException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service";
        String username = "root";
        String password = "anubhav08";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "SELECT * FROM employee_payroll WHERE Name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String employeeName = resultSet.getString("Name");
                    double salary = resultSet.getDouble("Salary");
                    Date start = resultSet.getDate("Start");

                    EmpPayroll employee = new EmpPayroll();
                    employee.setId(id);
                    employee.setName(employeeName);
                    employee.setSalary(salary);
                    employee.setStart(start);

                    System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary + ", Start Date: " + start);

                    return employee;
                } else {
                    String msg = "Employee not found with name: " + name;
                    throw new PayrollServiceException(msg);
                }
            }
        } catch (SQLException e) {
            throw new PayrollServiceException("Database error occurred while retrieving employee data.");
        }
    }
}