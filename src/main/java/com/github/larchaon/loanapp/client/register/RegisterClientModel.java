package com.github.larchaon.loanapp.client.register;

import javax.validation.constraints.NotNull;

public class RegisterClientModel {

    private long personalCode;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;
    @NotNull
    private String password;

    public long getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(long personalCode) {
        this.personalCode = personalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
