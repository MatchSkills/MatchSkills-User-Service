package com.matchskills.user.service.dtos;

import com.matchskills.user.service.entitys.CandidateEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCandidateRequest{

    @Size(min = 1, max = 250, message = "The name must be a maximum of 250 characters.")
    @NotBlank(message = "Candidate must have a name")
    private String name;

    @Size(min = 1, max = 250, message = "The email must be a maximum of 250 characters.")
    @Email(message = "Candidate must have a email formated correctly")
    @NotBlank(message = "Candidate must have a email")
    private String email;

    @Size(min = 1, max = 250, message = "The password must be a maximum of 250 characters.")
    @NotBlank(message = "Candidate must have a password")
    private String password;

    @NotBlank(message = "Candidate must have a number")
    private String number;

    public CandidateEntity toCandidateEntity() {
        return new CandidateEntity(name, email, password, number);
    }

}