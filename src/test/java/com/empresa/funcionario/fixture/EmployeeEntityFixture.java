package com.empresa.funcionario.fixture;

import com.empresa.funcionario.repository.entities.EmployeeEntity;

public class EmployeeEntityFixture {

    public static EmployeeEntity complete() {
        return EmployeeEntity.builder()
                .name("João")
                .lastName("Silva")
                .email("joao@gmail.com")
                .nisNumber("12345678910")
                .build();
    }
}
