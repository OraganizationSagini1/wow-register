package com.cognizant.wow.employeeregister;

import org.springframework.data.repository.CrudRepository;



public interface EmployeeRepository  extends CrudRepository<Employee, Long> {
}
