package com.day15;

import java.sql.SQLException;
import java.util.Map;

public class EPService {
    public static void main(String[] args) {
        PayrollDBService service = PayrollDBService.getInstance();
        try {
            Map<String, Map<String, Double>> stats = service.getSalaryStatsByGender();
            stats.forEach((gender, statistics) -> {
                System.out.println("Gender: " + gender);
                statistics.forEach((key, value) -> System.out.println(key + ": " + value));
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}