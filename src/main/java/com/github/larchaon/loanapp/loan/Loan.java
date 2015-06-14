package com.github.larchaon.loanapp.loan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Loan {

    @Id
    @GeneratedValue
    private long pk;
    private long clientId;
    private long amount;
//    @NotNull
    private Date signedOn;
//    @NotNull
    private Date expiresOn;

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getSignedOn() {
        return signedOn;
    }

    public void setSignedOn(Date signedOn) {
        this.signedOn = signedOn;
    }

    public Date getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(Date expiresOn) {
        this.expiresOn = expiresOn;
    }
}
