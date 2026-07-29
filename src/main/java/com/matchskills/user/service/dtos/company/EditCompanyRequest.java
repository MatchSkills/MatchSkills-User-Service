package com.matchskills.user.service.dtos.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditCompanyRequest {

    @NotNull(message = "ID cannot be null")
    @Positive(message = "ID must be greater than zero")
    private Long id;

    @Size(min = 1, max = 250, message = "The name must be a maximum of 250 characters.")
    @NotBlank(message = "Company must have a name")
    private String name;

    @Size(min = 1, max = 250, message = "The email must be a maximum of 250 characters.")
    @NotBlank(message = "Company must have a email")
    private String email;

    @Size(min = 1, max = 500, message = "The address must be a maximum of 500 characters.")
    @NotBlank(message = "Company must have a address")
    private String address;

}
