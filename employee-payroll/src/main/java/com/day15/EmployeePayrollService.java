package com.day15;

import java.util.Scanner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollService {

    private List<EmployeePayroll> employeePayrollList;

    public EmployeePayrollService() {
        this.employeePayrollList = new ArrayList<>();
    }

    public void addEmployee(EmployeePayroll employee) {
        employeePayrollList.add(employee);
    }
}