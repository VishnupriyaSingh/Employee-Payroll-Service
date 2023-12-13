package com.day15;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PayrollDBService {
    private static PayrollDBService instance;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public PayrollDBService() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "Priya@002";

        try {
            this.connection = DriverManager.getConnection(jdbcUrl, username, password);
            this.preparedStatement = connection.prepareStatement("SELECT * FROM employee_payroll WHERE Name = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static PayrollDBService getInstance() {
        if (instance == null) {
            instance = new PayrollDBService();
        }
        return instance;
    }

    public EmpPayroll getEmployeeDataByName(String name) throws SQLException {
        preparedStatement.setString(1, name);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return new EmpPayroll(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("gender"),
                        resultSet.getDouble("salary"),
                        resultSet.getDate("Start"),
                        resultSet.getString("phone"),
                        resultSet.getString("address"),
                        resultSet.getString("department"),
                        resultSet.getDouble("basicPay"),
                        resultSet.getDouble("deductions"),
                        resultSet.getDouble("taxablePay"),
                        resultSet.getDouble("incomeTax"),
                        resultSet.getDouble("netPay"));
            }
        }
        return null;
    }

    // UC5
    public List<EmpPayroll> getEmployeesJoinedInRange(Date startDate, Date endDate) throws SQLException {
        List<EmpPayroll> employeeList = new ArrayList<>();
        String query = "SELECT * FROM employee_payroll WHERE Start BETWEEN ? AND ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                EmpPayroll employee = new EmpPayroll(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("gender"),
                        resultSet.getDouble("salary"),
                        resultSet.getDate("Start"),
                        resultSet.getString("phone"),
                        resultSet.getString("address"),
                        resultSet.getString("department"),
                        resultSet.getDouble("basicPay"),
                        resultSet.getDouble("deductions"),
                        resultSet.getDouble("taxablePay"),
                        resultSet.getDouble("incomeTax"),
                        resultSet.getDouble("netPay"));
                employeeList.add(employee);
            }
        }
        return employeeList;
    }

    // UC6
    public Map<String, Map<String, Double>> getSalaryStatsByGender() throws SQLException {
        Map<String, Map<String, Double>> stats = new HashMap<>();
        String query = "SELECT gender, SUM(salary) as total_salary, AVG(salary) as avg_salary, MIN(salary) as min_salary, MAX(salary) as max_salary, COUNT(*) as employee_count FROM employee_payroll GROUP BY gender";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                Map<String, Double> genderStats = new HashMap<>();
                genderStats.put("sum", resultSet.getDouble("total_salary"));
                genderStats.put("avg", resultSet.getDouble("avg_salary"));
                genderStats.put("min", resultSet.getDouble("min_salary"));
                genderStats.put("max", resultSet.getDouble("max_salary"));
                genderStats.put("count", (double) resultSet.getInt("employee_count"));

                stats.put(gender, genderStats);
            }
        }
        return stats;
    }
}