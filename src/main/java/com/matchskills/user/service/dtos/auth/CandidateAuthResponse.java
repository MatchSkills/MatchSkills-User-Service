package com.matchskills.user.service.dtos.auth;

import com.matchskills.user.service.dtos.candidate.CandidateResponse;
import com.matchskills.user.service.dtos.tokens.TokensResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CandidateAuthResponse {

    private TokensResponse tokens;
    private CandidateResponse candidate;

}
