package com.cognizant.wow.employeeregister;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface EmployeeBadgeMappingRepository extends CrudRepository<EmployeeBadgeMapping, Long> {
    EmployeeBadgeMapping getByEmployeeIdAndDate(Long employeeId, LocalDate date);
}
