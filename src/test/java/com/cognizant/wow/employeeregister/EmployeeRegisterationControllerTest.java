package com.cognizant.wow.employeeregister;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeRegisterationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BadgeRepository badgeRepository;
    @Autowired
    private EmployeeBadgeMappingRepository employeeBadgeMappingRepository;

    private static final String ERROR_MESSAGE ="Employee details can't be blank";
    private static final String USER_REGISTERED_MESSAGE ="Employee already registered";
    @Before
    public void before(){
        employeeRepository.deleteAll();
        badgeRepository.deleteAll();
        employeeBadgeMappingRepository.deleteAll();
    }
    @After
    public void after(){
        employeeRepository.deleteAll();
        badgeRepository.deleteAll();
        employeeBadgeMappingRepository.deleteAll();
    }
    @Test
    public void registerEmployeeReturnErrorForNoEmployeeData() throws Exception {
        //set up
        Employee employee = new Employee();
        LocalDate localDate = LocalDate.now();
        System.out.println("@@@@@@@@@@@@@@@@@@@@"+localDate);
        //exercise
        String response=mockMvc.perform(MockMvcRequestBuilders
                .post("/register").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getErrorMessage();
                //.getContentAsString();

        //Assert
        assertThat(response, is(ERROR_MESSAGE));


    }
    @Test
    public void registerEmployeeFetchActiveBadgeWithLeastNumberForEmployeeOnCheckin() throws Exception{
        //set up
        Employee employee = new Employee("Fine", "Moe", 123456L, "9876543210");
        employeeRepository.save(employee);
        Badge badge1 = new Badge(4L, "InActive","UnAssigned");
        badgeRepository.save(badge1);
        Badge badge3 = new Badge(46L, "Active","UnAssigned");
        badgeRepository.save(badge3);
        Badge badge2 = new Badge(56L, "Active","UnAssigned");

        badgeRepository.save(badge2);



        //exercise
        String response=mockMvc.perform(MockMvcRequestBuilders
                .post("/register")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Badge actual = objectMapper.readValue(response,Badge.class);




        //Assert
        badge3.setAssigned("Assigned");
        assertThat(badge3, is(actual));
    }
    @Test
    public void registerEmployeeAssignActiveBadgeWithLeastNumberForEmployeeOnCheckin() throws Exception{
        //set up
        Employee employee = new Employee("Fine", "Moe", 123456L, "9876543210");
        employeeRepository.save(employee);
        Badge badge1 = new Badge(4L, "InActive","UnAssigned");
        badgeRepository.save(badge1);
        Badge badge3 = new Badge(46L, "Active","UnAssigned");
        badgeRepository.save(badge3);
        Badge badge2 = new Badge(56L, "Active","UnAssigned");

        badgeRepository.save(badge2);

        EmployeeBadgeMapping expected = new EmployeeBadgeMapping(123456L,46L,LocalDate.now());



        //exercise
        String response=mockMvc.perform(MockMvcRequestBuilders
                .post("/register")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        EmployeeBadgeMapping actual = employeeBadgeMappingRepository.findAll().iterator().next();




        //Assert

        assertThat(expected.getEmployeeId(), is(actual.getEmployeeId()));
        assertThat(expected.getBadgeId(), is(actual.getBadgeId()));
        assertThat(expected.getDate(), is(actual.getDate()));
    }



    @Test
    public void registerEmployeeReturnSucessfullAdd() throws Exception {
        //set up
        Employee employee = new Employee("Fine", "Moe", 123456L, "9876543210");
        Badge badge1 = new Badge(4L, "Active","UnAssigned");
        badgeRepository.save(badge1);
        //exercise
        String response=mockMvc.perform(MockMvcRequestBuilders
                .post("/register").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Employee actual1=employeeRepository.findById(123456L).orElseThrow(()->new RuntimeException("No Employee data available"));


        //Assert
        assertThat(employee, is(actual1));


    }

//    @Test
//    public void registerEmployeeReturnErrorMessageIfEmployeeExist() throws Exception {
//        //set up
//        Employee employee = new Employee("John", "Smith", 123456L, "9876543210");
//
//        //exercise
//        employeeRepository.save(employee);
//        //exercise
//        String response=mockMvc.perform(MockMvcRequestBuilders
//                .post("/register").accept(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(employee))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andReturn()
//                .getResponse()
//                .getErrorMessage();
//        //.getContentAsString();
//
//        //Assert
//        assertThat(response, is(USER_REGISTERED_MESSAGE));
//
//
//    }
@Test
public void registerEmployeeReturnSameBadheNumberOnRepeatedCheckinOnSameDay() throws Exception{
    //set up
    Employee employee = new Employee("Fine", "Moe", 123456L, "9876543210");
    employeeRepository.save(employee);
    Badge badge1 = new Badge(4L, "InActive","UnAssigned");
    badgeRepository.save(badge1);
    Badge badge3 = new Badge(46L, "Active","Assigned");
    badgeRepository.save(badge3);
    Badge badge2 = new Badge(56L, "Active","UnAssigned");

    badgeRepository.save(badge2);
    employeeBadgeMappingRepository.save(new EmployeeBadgeMapping(employee.getEmployeeId(),badge3.getBadgeId(),LocalDate.now()));




    //exercise
    String response=mockMvc.perform(MockMvcRequestBuilders
            .post("/register")
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(employee))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
    Badge actual = objectMapper.readValue(response,Badge.class);




    //Assert
    assertThat(actual, is(badge3));
}
}
