package com.empresa.funcionario.repository.impl;

import com.empresa.funcionario.domains.Employee;
import com.empresa.funcionario.mappers.EmployeeMapper;
import com.empresa.funcionario.repository.EmployeeRepository;
import com.empresa.funcionario.repository.jpa.EmployeeJPARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeJPARepository jpaRepository;
    private final EmployeeMapper mapper;

    @Override
    public void saveEmployee(final Employee employee) {
        jpaRepository.save(mapper.toEntity(employee));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return mapper.fromEntityList(jpaRepository.findAll());
    }

    @Override
    public Employee getEmployeeById(final Long id) {
        return mapper.fromEntity(jpaRepository.findById(id));
    }

    @Override
    public Employee updateEmployee(final Long id, final Employee employee) {
        jpaRepository.updateEmployee(id, mapper.toEntity(employee));
        return getEmployeeById(id);
    }

    @Override
    public void deleteEmployee(final Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Employee findByEmail(final String email) {
        return mapper.fromEntity(jpaRepository.findByEmail(email));
    }

    @Override
    public boolean existById(Long id) {
        return jpaRepository.existsById(id);
    }
}
