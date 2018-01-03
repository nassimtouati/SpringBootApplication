package com.touati.org.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class JobHistoryPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "EMPLOYEE_ID")
    private long employeeId;

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE")
    private Date startDate;

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JobHistoryPK)) {
            return false;
        }
        JobHistoryPK castOther = (JobHistoryPK) other;
        return
                (this.employeeId == castOther.employeeId)
                        && this.startDate.equals(castOther.startDate);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + ((int) (this.employeeId ^ (this.employeeId >>> 32)));
        hash = hash * prime + this.startDate.hashCode();

        return hash;
    }
}