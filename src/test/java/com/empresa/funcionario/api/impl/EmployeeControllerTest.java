package com.empresa.funcionario.api.impl;

import com.empresa.funcionario.api.model.request.EmployeeRequest;
import com.empresa.funcionario.api.model.response.EmployeeResponse;
import com.empresa.funcionario.domains.Employee;
import com.empresa.funcionario.fixture.EmployeeFixture;
import com.empresa.funcionario.fixture.EmployeeRequestFixture;
import com.empresa.funcionario.fixture.EmployeeResponseFixture;
import com.empresa.funcionario.mappers.EmployeeMapper;
import com.empresa.funcionario.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@MockitoSettings
public class EmployeeControllerTest {

    @InjectMocks
    EmployeeController controller;

    @Mock
    EmployeeMapper mapper;

    @Mock
    EmployeeService service;

    @Test
    void shouldSaveEmployee() {
        final EmployeeRequest request = EmployeeRequestFixture.complete();
        final Employee employee = EmployeeFixture.complete();

        doReturn(employee)
                .when(mapper).toEmployee(request);
        doNothing().when(service).saveEmployee(employee);

        final ResponseEntity<Void> response = controller.saveEmployee(request);

        verify(mapper, times(1)).toEmployee(request);
        verify(service, times(1)).saveEmployee(employee);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldGetAllEmployee() {
        final List<Employee> employeeList = List.of(EmployeeFixture.complete());
        final List<EmployeeResponse> employeeResponseList = List.of(EmployeeResponseFixture.complete());

        doReturn(employeeList)
                .when(service)
                .getAllEmployees();
        doReturn(employeeResponseList)
                .when(mapper)
                .fromDomainList(employeeList);

        final ResponseEntity<List<EmployeeResponse>> response = controller.getEmployees();

        verify(mapper, times(1)).fromDomainList(employeeList);
        verify(service, times(1)).getAllEmployees();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldGetEmployeeById() {
        final Employee employee = EmployeeFixture.complete();
        final EmployeeResponse employeeResponse = EmployeeResponseFixture.complete();

        doReturn(employee)
                .when(service)
                .getEmployeeById(1L);
        doReturn(employeeResponse)
                .when(mapper)
                .fromDomain(employee);

        final ResponseEntity<EmployeeResponse> response = controller.getEmployeeById(1L);

        verify(mapper, times(1)).fromDomain(employee);
        verify(service, times(1)).getEmployeeById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldUpdateEmployee() {
        final EmployeeRequest request = EmployeeRequestFixture.complete();
        final Employee employee = EmployeeFixture.complete();

        doReturn(employee)
                .when(mapper).toEmployee(request);
        doReturn(EmployeeFixture.complete()).when(service).updateEmployee(1L, employee);

        final ResponseEntity<EmployeeResponse> response = controller.updateEmployeeById(1L, request);

        verify(mapper, times(1)).toEmployee(request);
        verify(service, times(1)).updateEmployee(1L, employee);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldDeleteEmployee() {
        doNothing().when(service).deleteEmployee(1L);

        final ResponseEntity<Void> response = controller.deleteEmployeeById(1L);

        verify(service, times(1)).deleteEmployee(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
