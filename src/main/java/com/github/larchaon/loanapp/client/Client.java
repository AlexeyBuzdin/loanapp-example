package com.github.larchaon.loanapp.client;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private long pk;
    private long personalCode;
    @NotNull
    private String phoneNumber;
//    @NotNull
    private String fullName;
    @NotNull
    private String email;
    @NotNull
    private String password;

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

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
