package com.empresa.funcionario.fixture;

import com.empresa.funcionario.api.model.response.EmployeeResponse;

public class EmployeeResponseFixture {

    public static EmployeeResponse complete() {
        return EmployeeResponse.builder()
                .id(1L)
                .name("Joao")
                .lastName("Silva")
                .email("joao@gmail.com")
                .nisNumber("12345678910")
                .build();
    }
}
