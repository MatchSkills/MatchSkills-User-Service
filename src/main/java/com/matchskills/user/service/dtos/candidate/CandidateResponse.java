package com.matchskills.user.service.dtos.candidate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CandidateResponse {

    private Long id;
    private String name;
    private String email;
    private String number;

}
