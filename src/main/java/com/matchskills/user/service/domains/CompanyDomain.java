package com.matchskills.user.service.domains;

import com.matchskills.user.service.dtos.company.CompanyResponse;
import com.matchskills.user.service.enums.RoleType;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyDomain implements UserDetails {

    private Long id;
    private String name;
    private String cnpj;
    private String email;
    private String address;

    public CompanyResponse toCompanyResponse() {
        return new CompanyResponse(this.id,this.name,this.cnpj,this.email,this.address);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + RoleType.Company.name()));
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
