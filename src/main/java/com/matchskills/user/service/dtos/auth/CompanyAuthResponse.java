package com.matchskills.user.service.dtos.auth;

import com.matchskills.user.service.dtos.company.CompanyResponse;
import com.matchskills.user.service.dtos.tokens.TokensResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CompanyAuthResponse {

    private TokensResponse tokens;
    private CompanyResponse company;

}
