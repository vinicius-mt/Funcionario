package com.empresa.funcionario.service.impl;

import com.empresa.funcionario.domains.Employee;
import com.empresa.funcionario.exceptions.InvalidDataException;
import com.empresa.funcionario.repository.EmployeeRepository;
import com.empresa.funcionario.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public void saveEmployee(final Employee employee) {
        log.info("Saving new Employee");
        validateEmployeeData(employee);
        validateExistEmail(employee.getEmail());
        repository.saveEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        log.info("Getting all employee");
        return repository.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(final Long id) {
        log.info("Getting employee by id {}", id);
        return repository.getEmployeeById(id);
    }

    @Override
    public Employee updateEmployee(final Long id, final Employee employee) {
        log.info("Updating employee for id {}", id);
        validateEmployeeData(employee);
        validateExistEmail(employee.getEmail());

        if (!repository.existById(id)) {
            throw new InvalidDataException("There is no employee for this id");
        }
        return repository.updateEmployee(id, employee);
    }

    @Override
    public void deleteEmployee(final Long id) {
        log.info("Deleting employee for id {}", id);
        if (!repository.existById(id)) {
            throw new InvalidDataException("There is no employee for this id");
        }
        repository.deleteEmployee(id);
    }

    private void validateEmployeeData(final Employee employee) {
        log.info("Validating employee data");
        if (!StringUtils.hasText(employee.getName()) || employee.getName().length() < 2 || employee.getName().length() > 30) {
            throw new InvalidDataException("The name must be between 2 and 30 characters.");
        }

        if (!StringUtils.hasText(employee.getLastName()) || employee.getLastName().length() < 2 || employee.getLastName().length() > 50) {
            throw new InvalidDataException("The last name must be between 2 and 50 characters.");
        }

        if (!StringUtils.hasText(employee.getEmail()) || !employee.getEmail().matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new IllegalArgumentException("The email is not valid");
        }

        if (!StringUtils.hasText(employee.getNisNumber()) || employee.getNisNumber().length() != 11) {
            throw new InvalidDataException("The Nis Number must contain 11 characters.");
        }
    }

    private void validateExistEmail(final String email) {
        log.info("Validating email registered");
        final Employee byEmail = repository.findByEmail(email);
        if(byEmail != null) {
            throw new InvalidDataException("Email already registered");
        }
    }
}
