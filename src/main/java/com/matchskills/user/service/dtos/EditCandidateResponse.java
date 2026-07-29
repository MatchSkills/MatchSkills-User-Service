package com.matchskills.user.service.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EditCandidateResponse {

    @NotNull(message = "ID cannot be null")
    @Positive(message = "ID must be greater than zero")
    private Long id;

    @Size(min = 1, max = 250, message = "The name must be a maximum of 250 characters.")
    @NotBlank(message = "Candidate must have a name")
    private String name;

    @Size(min = 1, max = 250, message = "The email must be a maximum of 250 characters.")
    @Email(message = "Candidate must have a email formated correctly")
    @NotBlank(message = "Candidate must have a email")
    private String email;

    @NotBlank(message = "Candidate must have a number")
    private String number;

}
