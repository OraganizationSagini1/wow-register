package com.cognizant.wow.employeeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("/register")
public class EmployeeRegisterationController {

    public final String ERROR_MESSAGE ="Employee details can't be blank";
    public final String USER_REGISTERED_MESSAGE ="Employee already registered";
    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping
    Employee registerEmployee(@RequestBody Employee employee){
        if(Objects.nonNull(employee)&& null!=employee.getEmployeeId()) {
            if(employeeRepository.existsById(employee.getEmployeeId())){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, USER_REGISTERED_MESSAGE);
            }

            return employeeRepository.save(employee);
        }

       else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ERROR_MESSAGE);
        }

    }
}
