package com.matchskills.user.service.dtos;

import com.matchskills.user.service.entitys.CandidateEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCandidateRequest{

    @NotBlank(message = "Candidate must have a name")
    private String name;

    @Email(message = "Candidate must have a email formated correctly")
    @NotBlank(message = "Candidate must have a email")
    private String email;

    @NotBlank(message = "Candidate must have a password")
    private String password;

    @NotBlank(message = "Candidate must have a number")
    private String number;

    public CandidateEntity toCandidateEntity() {
        return new CandidateEntity(name, email, password, number);
    }

}