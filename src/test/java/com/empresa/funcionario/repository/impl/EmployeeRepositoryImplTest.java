package com.empresa.funcionario.repository.impl;

import com.empresa.funcionario.domains.Employee;
import com.empresa.funcionario.fixture.EmployeeEntityFixture;
import com.empresa.funcionario.fixture.EmployeeFixture;
import com.empresa.funcionario.mappers.EmployeeMapper;
import com.empresa.funcionario.repository.entities.EmployeeEntity;
import com.empresa.funcionario.repository.jpa.EmployeeJPARepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@MockitoSettings
public class EmployeeRepositoryImplTest {

    @InjectMocks
    EmployeeRepositoryImpl repository;

    @Mock
    EmployeeJPARepository jpaRepository;

    @Mock
    EmployeeMapper mapper;

    @Test
    void shouldSaveEmployee() {
        final EmployeeEntity employeeEntity = EmployeeEntityFixture.complete();
        final Employee employee = EmployeeFixture.complete();

        doReturn(employeeEntity)
                .when(mapper).toEntity(employee);
        doReturn(employeeEntity).when(jpaRepository).save(employeeEntity);

        repository.saveEmployee(employee);

        verify(mapper, times(1)).toEntity(employee);
        verify(jpaRepository, times(1)).save(employeeEntity);
    }

    @Test
    void shouldGetAllEmployees() {
        final List<Employee> employeeList = List.of(EmployeeFixture.complete());
        final List<EmployeeEntity> employeeEntityList = List.of(EmployeeEntityFixture.complete());

        doReturn(employeeList).when(mapper).fromEntityList(employeeEntityList);
        doReturn(employeeEntityList).when(jpaRepository).findAll();

        final List<Employee> response = repository.getAllEmployees();

        verify(mapper, times(1)).fromEntityList(employeeEntityList);
        verify(jpaRepository, times(1)).findAll();

        assertEquals(employeeList, response);
    }

    @Test
    void shouldGetEmployeeById() {
        final Employee employee = EmployeeFixture.complete();
        final EmployeeEntity employeeEntity = EmployeeEntityFixture.complete();

        doReturn(employee).when(mapper).fromEntity(Optional.of(employeeEntity));
        doReturn(Optional.of(employeeEntity)).when(jpaRepository).findById(1L);

        final Employee response = repository.getEmployeeById(1L);

        verify(mapper, times(1)).fromEntity(Optional.of(employeeEntity));
        verify(jpaRepository, times(1)).findById(1L);

        assertEquals(employee, response);
    }

    @Test
    void shouldUpdateEmployee() {
        final Employee employee = EmployeeFixture.complete();
        final EmployeeEntity employeeEntity = EmployeeEntityFixture.complete();

        doReturn(employeeEntity).when(mapper).toEntity(employee);
        doNothing().when(jpaRepository).updateEmployee(1L, employeeEntity);
        doReturn(employee).when(mapper).fromEntity(Optional.of(employeeEntity));
        doReturn(Optional.of(employeeEntity)).when(jpaRepository).findById(1L);

        final Employee response = repository.updateEmployee(1L, employee);

        verify(mapper, times(1)).toEntity(employee);
        verify(jpaRepository, times(1)).updateEmployee(1L, employeeEntity);
        verify(mapper, times(1)).fromEntity(Optional.of(employeeEntity));
        verify(jpaRepository, times(1)).findById(1L);

        assertEquals(employee, response);
    }

    @Test
    void shouldDeleteEmployee() {
        doNothing().when(jpaRepository).deleteById(1L);

        repository.deleteEmployee(1L);

        verify(jpaRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldGetEmployeeByEmail() {
        final Employee employee = EmployeeFixture.complete();
        final EmployeeEntity employeeEntity = EmployeeEntityFixture.complete();

        doReturn(employee).when(mapper).fromEntity(employeeEntity);
        doReturn(employeeEntity).when(jpaRepository).findByEmail(employee.getEmail());

        final Employee response = repository.findByEmail(employee.getEmail());

        verify(mapper, times(1)).fromEntity(employeeEntity);
        verify(jpaRepository, times(1)).findByEmail(employee.getEmail());

        assertEquals(employee, response);
    }

    @Test
    void shouldValidateExistEmployee() {
        doReturn(true).when(jpaRepository).existsById(1L);

        final boolean response = repository.existById(1L);

        verify(jpaRepository, times(1)).existsById(1L);
        assertTrue(response);
    }

}
