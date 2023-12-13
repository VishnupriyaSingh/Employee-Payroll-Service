package com.day15;

import java.util.Date;
import java.util.List;

public class EmployeePayroll2 {
    private int id;
    private String name;
    private String gender;
    private double salary;
    private Date startDate;
    private String phone;
    private String address;
    private String department;
    private double basicPay;
    private double deductions;
    private double taxablePay;
    private double incomeTax;
    private double netPay;
    private List<String> departments; //UC9

    public EmployeePayroll2()
    {

    }

    public EmployeePayroll2(int id, String name, String gender, double salary, Date startDate, String phone, String address, String department, double basicPay, double deductions, double taxablePay, double incomeTax, double netPay, List<String> departments) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.startDate = startDate;
        this.phone = phone;
        this.address = address;
        this.department = department;
        this.basicPay = basicPay;
        this.deductions = deductions;
        this.taxablePay = taxablePay;
        this.incomeTax = incomeTax;
        this.netPay = netPay;
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "EmployeePayroll{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", startDate=" + startDate +
                ", phone=" + phone +
                ", address=" + address +
                ", department=" + department +
                ", basicPay=" + basicPay +
                ", deductions=" + deductions +
                ", taxablePay=" + taxablePay +
                ", incomeTax=" + incomeTax +
                ", netPay=" + netPay +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setStart(java.sql.Date start) {
        this.startDate = start;
    }

    public String getName() {
        return name;
    }

    public Date getStart() {
        return startDate;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }

    public void addDepartment(String department) {
        this.departments.add(department);
    }
}
