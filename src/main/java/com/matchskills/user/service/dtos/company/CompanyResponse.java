package com.matchskills.user.service.dtos.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CompanyResponse {

    private Long id;
    private String name;
    private String cnpj;
    private String email;
    private String address;

}
