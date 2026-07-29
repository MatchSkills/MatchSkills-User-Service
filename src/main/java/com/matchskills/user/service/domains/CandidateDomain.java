package com.matchskills.user.service.domains;

import com.matchskills.user.service.dtos.candidate.CandidateResponse;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
public class CandidateDomain {

    private Long id;
    private String name;
    private String email;
    private String number;

    public CandidateResponse toCandidateResponse(){
        return new CandidateResponse(this.id, this.name, this.email, this.number);
    }

}
