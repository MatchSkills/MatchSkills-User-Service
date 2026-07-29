package com.matchskills.user.service.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotBlank(message = "Candidate must have a name")
    private String name;

    @Email(message = "Candidate must have a email formated correctly")
    @NotBlank(message = "Candidate must have a email")
    private String email;

    @NotBlank(message = "Candidate must have a number")
    private String number;

}
