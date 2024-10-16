package com.empresa.funcionario.repository.jpa;

import com.empresa.funcionario.repository.entities.EmployeeEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeJPARepository extends JpaRepository<EmployeeEntity, Long> {

    @Modifying
    @Transactional
    @Query(
            value = """
                 UPDATE employee
                 SET
                     name = :#{#employee.name},
                     last_name = :#{#employee.lastName},
                     email = :#{#employee.email},
                     nis_number= :#{#employee.nisNumber}
                 WHERE id = :id
            """, nativeQuery = true
    )
    void updateEmployee(@Param("id") final Long id, @Param("employee") final EmployeeEntity employee);

    EmployeeEntity findByEmail(final String email);

}
