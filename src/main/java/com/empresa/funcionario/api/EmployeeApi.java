package com.empresa.funcionario.api;

import com.empresa.funcionario.api.model.request.EmployeeRequest;
import com.empresa.funcionario.api.model.response.EmployeeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/api/employee")
public interface EmployeeApi {

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<Void> saveEmployee(@RequestBody final EmployeeRequest request);

    @GetMapping
    ResponseEntity<List<EmployeeResponse>> getEmployees();

    @GetMapping(path = "{id}")
    ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable final Long id);

    @PutMapping(path = "/{id}")
    ResponseEntity<EmployeeResponse> updateEmployeeById(@PathVariable final Long id,
                                                        @RequestBody final EmployeeRequest requestUpdate);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    ResponseEntity<Void> deleteEmployeeById(@PathVariable final Long id);
}
