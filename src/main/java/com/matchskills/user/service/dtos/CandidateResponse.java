package com.matchskills.user.service.dtos;

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
