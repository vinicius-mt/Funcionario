package com.empresa.funcionario.service.impl;

import com.empresa.funcionario.domains.Employee;
import com.empresa.funcionario.exceptions.InvalidDataException;
import com.empresa.funcionario.fixture.EmployeeFixture;
import com.empresa.funcionario.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@MockitoSettings
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeServiceImpl service;

    @Mock
    EmployeeRepository repository;

    @Test
    void shouldSaveEmployee() {
        final Employee employee = EmployeeFixture.complete();

        doNothing().when(repository).saveEmployee(employee);
        doReturn(null).when(repository).findByEmail(employee.getEmail());

        service.saveEmployee(employee);

        verify(repository, times(1)).saveEmployee(employee);
        verify(repository, times(1)).findByEmail(employee.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenNameLess2Characters() {
        Employee employee = EmployeeFixture.complete();
        employee.setName("J");

        assertThrows(InvalidDataException.class, () -> service.saveEmployee(employee));
    }

    @Test
    void shouldGetAllEmployee() {
        doReturn(List.of(EmployeeFixture.complete())).when(repository).getAllEmployees();

        final List<Employee> allEmployees = service.getAllEmployees();

        verify(repository, times(1)).getAllEmployees();
        assertEquals(List.of(EmployeeFixture.complete()), allEmployees);
    }

    @Test
    void shouldUpdateEmployee() {
        final Employee employee = EmployeeFixture.complete();

        doReturn(true).when(repository).existById(1L);
        doReturn(employee).when(repository).updateEmployee(1L, employee);

        final Employee response = service.updateEmployee(1L, employee);

        verify(repository, times(1)).existById(1L);
        verify(repository, times(1)).updateEmployee(1L, employee);
        assertEquals(employee, response);
    }

    @Test
    void shouldDeleteEmpoyee() {
        doReturn(true).when(repository).existById(1L);
        doNothing().when(repository).deleteEmployee(1L);

        service.deleteEmployee(1L);

        verify(repository, times(1)).existById(1L);
        verify(repository, times(1)).deleteEmployee(1L);
    }

}
