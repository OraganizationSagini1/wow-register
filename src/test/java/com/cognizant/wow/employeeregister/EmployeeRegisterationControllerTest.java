package com.cognizant.wow.employeeregister;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeRegisterationControllerTest {
    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    public final String ERROR_MESSAGE ="Employee details can't be blank";
    @Test
    public void registerEmployeeReturnErrorForNoEmployeeData() throws Exception {
        //set up
        Employee employee = new Employee();
        //exercise
        String response=mockMvc.perform(MockMvcRequestBuilders
                .post("/register").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Assert
        assertThat(response, is(ERROR_MESSAGE));


    }

    @Test
    public void registerEmployeeReturnSucessfullAdd() throws Exception {
        //set up
        Employee employee = new Employee("Fine", "Moe", 123456L, "9876543210");
        //exercise
        String response=mockMvc.perform(MockMvcRequestBuilders
                .post("/register").accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Employee actual = objectMapper.readValue(response,Employee.class);


        //Assert
        assertThat(employee, is(actual));


    }
}
