package com.empresa.funcionario.fixture;

import com.empresa.funcionario.api.model.request.EmployeeRequest;

public class EmployeeRequestFixture {

    public static EmployeeRequest complete() {
        return EmployeeRequest.builder()
                .name("João")
                .lastName("Silva")
                .email("joao@gmail.com")
                .nisNumber("12345678910")
                .build();
    }
}
