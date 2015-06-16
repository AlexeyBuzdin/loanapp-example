package com.github.larchaon.loanapp.transaction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "my_shop",
        indexes = {
                @Index(columnList = "createdOn", name = "createdOn_x"),
                @Index(columnList = "remoteAddress", name = "remoteAddress_x")
        }
)
public class Transaction {
    @Id
    private long pk;
    private String remoteAddress;
    @Temporal(TemporalType.DATE)
    private Date createdOn;
    private int transactionCount;

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
