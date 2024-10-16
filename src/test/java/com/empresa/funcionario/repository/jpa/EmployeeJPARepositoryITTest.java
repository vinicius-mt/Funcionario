package com.empresa.funcionario.repository.jpa;

import com.empresa.funcionario.fixture.EmployeeEntityFixture;
import com.empresa.funcionario.repository.PostgreSqlContainerTest;
import com.empresa.funcionario.repository.entities.EmployeeEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeJPARepositoryITTest extends PostgreSqlContainerTest {

    @Autowired
    EmployeeJPARepository repository;

    @Test
    @Transactional
    @Sql("file:src/test/resources/db/scripts/employee.sql")
    void shouldSaveEmployee() {
        final List<EmployeeEntity> allBefore = repository.findAll();

        final EmployeeEntity employee = EmployeeEntityFixture.complete();
        repository.save(employee);

        final List<EmployeeEntity> allAfter = repository.findAll();

        assertEquals(1, allBefore.size());
        assertEquals(2, allAfter.size());
    }

    @Test
    @Transactional
    @Sql("file:src/test/resources/db/scripts/employee.sql")
    void shouldGetEmployeeById() {
        final Optional<EmployeeEntity> response = repository.findById(1L);
        assertEquals("Maria", response.get().getName());
    }
}
