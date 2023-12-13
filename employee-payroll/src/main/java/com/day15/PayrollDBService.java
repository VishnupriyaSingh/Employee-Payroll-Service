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

    // UC7
    public EmpPayroll addEmployeeToPayroll(int id, String name, double salary, Date startDate, String gender,
            String phone, String address, String department, double basicPay, double deductions, double taxablePay,
            double incomeTax, double netPay) throws SQLException {
        String insertQuery = "INSERT INTO employee_payroll (ID, Name, Salary, Start, Gender, Phone, Address, Department, BasicPay, Deductions, TaxablePay, IncomeTax, NetPay) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setDouble(3, salary);
            preparedStatement.setDate(4, new java.sql.Date(startDate.getTime()));
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, phone);
            preparedStatement.setString(7, address);
            preparedStatement.setString(8, department);
            preparedStatement.setDouble(9, basicPay);
            preparedStatement.setDouble(10, deductions);
            preparedStatement.setDouble(11, taxablePay);
            preparedStatement.setDouble(12, incomeTax);
            preparedStatement.setDouble(13, netPay);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Adding employee failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int ID = generatedKeys.getInt(1);
                    return new EmpPayroll(ID, name, gender, salary, startDate, phone, address, department,
                            basicPay, deductions, taxablePay, incomeTax, netPay);
                } else {
                    throw new SQLException("Adding employee failed, no ID obtained.");
                }
            }
        }
    }

    // UC8
    public EmpPayroll addEmployeeWithPayrollDetails(int id, String name, double salary, Date startDate,
            String gender, String phone, String address, String department, double basicPay, double deductions,
            double taxablePay, double incomeTax, double netPay) throws SQLException {
        String insertEmployeeQuery = "INSERT INTO employee_payroll (ID, Name, Salary, Start, Gender, Phone, Address, Department, BasicPay, Deductions, TaxablePay, IncomeTax, NetPay) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String insertPayrollDetailsQuery = "INSERT INTO payroll_details (employee_id, deductions, taxable_pay, tax, net_pay) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement employeeStatement = connection.prepareStatement(insertEmployeeQuery,
                Statement.RETURN_GENERATED_KEYS);
                PreparedStatement payrollDetailsStatement = connection.prepareStatement(insertPayrollDetailsQuery)) {

            connection.setAutoCommit(false);

            employeeStatement.setInt(1, id);
            employeeStatement.setString(2, name);
            employeeStatement.setDouble(3, salary);
            employeeStatement.setDate(4, new java.sql.Date(startDate.getTime()));
            employeeStatement.setString(5, gender);
            employeeStatement.setString(6, phone);
            employeeStatement.setString(7, address);
            employeeStatement.setString(8, department);
            employeeStatement.setDouble(9, basicPay);
            employeeStatement.setDouble(10, deductions);
            employeeStatement.setDouble(11, taxablePay);
            employeeStatement.setDouble(12, incomeTax);
            employeeStatement.setDouble(13, netPay);
            int employeeRowsAffected = employeeStatement.executeUpdate();

            if (employeeRowsAffected == 0) {
                throw new SQLException("Adding employee failed, no rows affected.");
            }

            int employeeId;
            try (ResultSet generatedKeys = employeeStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    employeeId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Adding employee failed, no ID obtained.");
                }
            }

            // Calculate payroll details
            double deductionsPD = salary * 0.2;
            double taxablePayPD = salary - deductionsPD;
            double tax = taxablePayPD * 0.1;
            double netPayPD = salary - tax;

            // Insert into payroll_details
            payrollDetailsStatement.setInt(1, employeeId);
            payrollDetailsStatement.setDouble(2, deductionsPD);
            payrollDetailsStatement.setDouble(3, taxablePayPD);
            payrollDetailsStatement.setDouble(4, tax);
            payrollDetailsStatement.setDouble(5, netPayPD);
            int payrollRowsAffected = payrollDetailsStatement.executeUpdate();

            if (payrollRowsAffected == 0) {
                throw new SQLException("Adding payroll details failed, no rows affected.");
            }

            connection.commit();

            return new EmpPayroll(id, name, gender, salary, startDate, phone, address, department, basicPay,
                    deductions, taxablePay, incomeTax, netPay);

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}