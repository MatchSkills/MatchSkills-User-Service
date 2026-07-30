package com.matchskills.user.service.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CandidateLoginRequest {

    @NotBlank(message = "Candidate must enter a email for login")
    private String email;

    @NotBlank(message = "Candidate must enter a password for login")
    private String password;

}
