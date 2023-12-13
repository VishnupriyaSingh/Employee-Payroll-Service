package com.day15;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public class EPService {

    public static void main(String[] args) {
        PayrollDBService payrollDBService = PayrollDBService.getInstance();
        try {
            Map<String, Map<String, Double>> salaryStats = payrollDBService.getSalaryStatsByGender();
            salaryStats.forEach((gender, stats) -> {
                System.out.println("Gender: " + gender);
                stats.forEach((stat, value) -> System.out.println(stat + ": " + value));
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}