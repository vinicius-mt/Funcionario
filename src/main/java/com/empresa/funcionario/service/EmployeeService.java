package com.empresa.funcionario.service;

import com.empresa.funcionario.domains.Employee;

import java.util.List;

public interface EmployeeService {

    void saveEmployee(final Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(final Long id);


    Employee updateEmployee(final Long id, final Employee employee);

    void deleteEmployee(final Long id);
}
