package com.matchskills.user.service.dtos.company;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateCompanyRequest {

    @Size(min = 1, max = 250, message = "The name must be a maximum of 250 characters.")
    @NotBlank(message = "Company must have a name")
    private String name;

    @Size(min = 14, max = 14, message = "The cnpj must be size of 14 characters.")
    @NotBlank(message = "Company must have a cnpj")
    private String cnpj;

    @Email
    @Size(min = 1, max = 250, message = "The email must be a maximum of 250 characters.")
    @NotBlank(message = "Company must have a email")
    private String email;

    @Size(min = 1, max = 250, message = "The password must be a maximum of 250 characters.")
    @NotBlank(message = "Company must have a password")
    private String password;

    @Size(min = 1, max = 500, message = "The addres must be a maximum of 500 characters.")
    @NotBlank(message = "Company must have a address")
    private String address;

}
