package com.empresa.funcionario.fixture;

import com.empresa.funcionario.domains.Employee;

public class EmployeeFixture {

    public static Employee complete() {
        return Employee.builder()
                .name("João")
                .lastName("Silva")
                .email("joao@gmail.com")
                .nisNumber("12345678910")
                .build();
    }
}
