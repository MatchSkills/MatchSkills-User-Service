package com.matchskills.user.service.domains;

import com.matchskills.user.service.dtos.company.CompanyResponse;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyDomain {

    private Long id;
    private String name;
    private String cnpj;
    private String email;
    private String address;

    public CompanyResponse toCompanyResponse() {
        return new CompanyResponse(this.id,this.name,this.cnpj,this.email,this.address);
    }

}
