package com.tasktracker.application.models;

public class UserView {

    public User user;

    public Float eff;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getEff() {
        return eff;
    }

    public void setEff(Float eff) {
        this.eff = eff;
    }

    public UserView(User user, Float eff) {
        this.user = user;
        this.eff = eff;
    }

}
