package com.example.groupassignment_beta;

public class HelperClass {

    String name, email, username, password, cookingLevel, halalPreference,vegetarianPreference;

    public HelperClass(String name, String username, String email, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.cookingLevel = "";
        this.halalPreference = "";
        this.vegetarianPreference = "";
    }

    public HelperClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHalalPreference() {
        return halalPreference;
    }

    public void setHalalPreference(String halalPreference) {
        this.halalPreference = halalPreference;
    }

    public String getVegetarianPreference() {
        return vegetarianPreference;
    }

    public void setVegetarianPreference(String vegetarianPreference) {
        this.vegetarianPreference = vegetarianPreference;
    }

    public String getCookingLevel() {
        return cookingLevel;
    }

    public void setCookingLevel(String cookingLevel) {
        this.cookingLevel = cookingLevel;
    }

}

