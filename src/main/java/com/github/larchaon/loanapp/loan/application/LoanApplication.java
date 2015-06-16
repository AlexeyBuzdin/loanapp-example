package com.github.larchaon.loanapp.loan.application;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "my_shop",
        indexes = {
                @Index(columnList = "createdOn", name = "createdOn_x"),
                @Index(columnList = "remoteAddress", name = "remoteAddress_x")
        }
)
public class LoanApplication {
    @Id
    private long pk;
    private long loanId;
    private String remoteAddress;
    @Temporal(TemporalType.DATE)
    private Date createdOn;

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
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
