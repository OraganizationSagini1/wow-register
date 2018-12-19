package com.cognizant.wow.employeeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/register")
public class EmployeeRegisterationController {

    private static final String ERROR_MESSAGE ="Employee details can't be blank";
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BadgeRepository badgeRepository;
    @Autowired
    private EmployeeBadgeMappingRepository employeeBadgeMappingRepository;

    @PostMapping
    Badge registerEmployee(@RequestBody Employee employee){
        if(Objects.nonNull(employee) && null!=employee.getEmployeeId() ) {
            if(!employeeRepository.existsById(employee.getEmployeeId())){
                employeeRepository.save(employee);
            }

            Badge badge= badgeRepository.findTopByStatusAndAssignedOrderByBadgeId("Active","UnAssigned");
            employeeBadgeMappingRepository.save(new EmployeeBadgeMapping(employee.getEmployeeId(),badge.getBadgeId(), LocalDate.now()));
            return badge;
        }

       else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ERROR_MESSAGE);
        }

    }
}
