package com.matchskills.user.service.domains;

import com.matchskills.user.service.dtos.candidate.CandidateResponse;
import com.matchskills.user.service.enums.RoleType;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
public class CandidateDomain implements UserDetails {

    private Long id;
    private String name;
    private String email;
    private String number;

    public CandidateResponse toCandidateResponse(){
        return new CandidateResponse(this.id, this.name, this.email, this.number);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + RoleType.Candidate.name()));
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
