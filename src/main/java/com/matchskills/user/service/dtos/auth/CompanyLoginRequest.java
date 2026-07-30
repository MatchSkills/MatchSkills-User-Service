package com.matchskills.user.service.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CompanyLoginRequest {

    @NotBlank(message = "Company must enter a cnpj for login")
    private String cnpj;
    @NotBlank(message = "Company must enter a password for login")
    private String password;

}
