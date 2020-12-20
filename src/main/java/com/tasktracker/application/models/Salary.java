package com.tasktracker.application.models;

public class Salary {

    private String fullname;

    private String salaryAmount;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(String salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public Salary(String fullname, String salaryAmount) {
        this.fullname = fullname;
        this.salaryAmount = salaryAmount;
    }

    public String CalculateSalary()
    {
        return new String();
    }
}
