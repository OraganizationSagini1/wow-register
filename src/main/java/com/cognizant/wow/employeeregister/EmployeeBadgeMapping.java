package com.cognizant.wow.employeeregister;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Date;
@Entity
 public class EmployeeBadgeMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SERIAL_SEQUENCE")
    private Long serialNumber;
    private Long employeeId;
    private Long badgeId;
    private LocalDate date;
    public EmployeeBadgeMapping(){}

    public EmployeeBadgeMapping(Long employeeId, Long badgeId, LocalDate date) {
        this.badgeId = badgeId;
        this.employeeId = employeeId;
        this.date = date;
    }


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Long badgeId) {
        this.badgeId = badgeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
