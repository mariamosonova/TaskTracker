package com.tasktracker.application.DTO;

import com.tasktracker.application.models.User;

public class BonusCalculationDTO {
    private static final String EMPTY = "";
    private User user;
    private String efficiency;
    private boolean isAssignBonus;

    public BonusCalculationDTO() {
        this.user = new User();
        this.efficiency = EMPTY;
        this.isAssignBonus = false;
    }

    public User getUser() {
        return user;
    }

    public BonusCalculationDTO setUser(User user) {
        this.user = user;
        return this;
    }


    public String getEfficiency() {
        return efficiency;
    }

    public BonusCalculationDTO setEfficiency(String efficiency) {
        this.efficiency = efficiency;
        return this;
    }


    public boolean isAssignBonus() {
        return isAssignBonus;
    }

    public BonusCalculationDTO setAssignBonus(boolean assignBonus) {
        isAssignBonus = assignBonus;
        return this;
    }
}
