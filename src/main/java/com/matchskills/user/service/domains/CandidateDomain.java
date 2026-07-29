package com.matchskills.user.service.domains;

import com.matchskills.user.service.dtos.CandidateResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CandidateDomain {

    private Long id;
    private String name;
    private String email;
    private String number;

    public CandidateResponse toCandidateResponse(){
        return new CandidateResponse(this.id, this.name, this.email, this.number);
    }

}
