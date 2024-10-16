package com.empresa.funcionario.mappers;

import com.empresa.funcionario.api.model.request.EmployeeRequest;
import com.empresa.funcionario.api.model.response.EmployeeResponse;
import com.empresa.funcionario.domains.Employee;
import com.empresa.funcionario.repository.entities.EmployeeEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(final EmployeeRequest request);

    List<EmployeeResponse> fromDomainList(final List<Employee> allEmployees);
    EmployeeResponse fromDomain(final Employee employee);

    EmployeeEntity toEntity(final Employee employee);

    List<Employee> fromEntityList(List<EmployeeEntity> employeeEntities);

    Employee fromEntity(final EmployeeEntity byId);

    default Employee fromEntity(final Optional<EmployeeEntity> optionalEmployeeEntity) {
        return optionalEmployeeEntity.map(this::fromEntity).orElse(null);
    }

}
