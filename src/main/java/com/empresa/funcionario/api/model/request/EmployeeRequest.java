package com.empresa.funcionario.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class EmployeeRequest {

    private String name;
    private String lastName;
    private String email;
    private String nisNumber;
}
