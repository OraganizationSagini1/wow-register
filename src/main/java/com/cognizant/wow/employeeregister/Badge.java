package com.cognizant.wow.employeeregister;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Badge {
    @Id
    @NotNull
    private Long badgeId;
    private String status;
    private String assigned;
    public Badge(){

    }
    public Badge(long badgeId, String status, String assigned) {
        this.assigned=assigned;
        this.badgeId=badgeId;
        this.status=status;
    }

    public Long getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Long badgeId) {
        this.badgeId = badgeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Badge badge = (Badge) o;
        return badgeId.equals(badge.badgeId) &&
                Objects.equals(status, badge.status) &&
                Objects.equals(assigned, badge.assigned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(badgeId, status, assigned);
    }

    @Override
    public String toString() {
        return "Badge{" +
                "badgeId=" + badgeId +
                ", status='" + status + '\'' +
                ", assigned='" + assigned + '\'' +
                '}';
    }
}
