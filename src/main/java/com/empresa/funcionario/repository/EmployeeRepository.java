package com.empresa.funcionario.repository;

import com.empresa.funcionario.domains.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository {
    void saveEmployee(final Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(final Long id);

    Employee updateEmployee(final Long id, final Employee employee);

    void deleteEmployee(final Long id);

    Employee findByEmail(final String email);

    boolean existById(final Long id);
}
