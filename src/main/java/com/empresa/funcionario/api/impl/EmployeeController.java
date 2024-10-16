package com.empresa.funcionario.api.impl;

import com.empresa.funcionario.api.EmployeeApi;
import com.empresa.funcionario.api.model.request.EmployeeRequest;
import com.empresa.funcionario.api.model.response.EmployeeResponse;
import com.empresa.funcionario.domains.Employee;
import com.empresa.funcionario.mappers.EmployeeMapper;
import com.empresa.funcionario.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeApi {

    private final EmployeeService service;
    private final EmployeeMapper mapper;

    @Override
    public ResponseEntity<Void> saveEmployee(final EmployeeRequest request) {
        service.saveEmployee(mapper.toEmployee(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<EmployeeResponse>> getEmployees() {
        return ResponseEntity.ok(mapper.fromDomainList(service.getAllEmployees()));
    }

    @Override
    public ResponseEntity<EmployeeResponse> getEmployeeById(final Long id) {
        final Employee response = service.getEmployeeById(id);

        if (response != null) {
            return ResponseEntity.ok(mapper.fromDomain(response));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<EmployeeResponse> updateEmployeeById(final Long id, final EmployeeRequest requestUpdate) {
        try {
            final Employee updatedEmployee = service.updateEmployee(id, mapper.toEmployee(requestUpdate));
            return ResponseEntity.ok(mapper.fromDomain(updatedEmployee));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ResponseEntity<Void> deleteEmployeeById(final Long id) {
        try {
            service.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw e;
        }
    }
}
