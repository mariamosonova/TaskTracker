package com.tasktracker.application.models;

public class Salary {

    private String fullname;

    private String salaryAmount;

    private String monthEff;

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

    public Salary(String fullname, String salaryAmount, String monthEff) {
        this.fullname = fullname;
        this.salaryAmount = salaryAmount;
        this.monthEff = monthEff;
    }

    public String CalculateSalary()
    {
        return new String();
    }

    public String getMonthEff() {
        return monthEff;
    }

    public void setMonthEff(String monthEff) {
        this.monthEff = monthEff;
    }
}
