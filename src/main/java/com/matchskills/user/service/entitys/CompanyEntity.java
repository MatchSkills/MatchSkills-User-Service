package com.matchskills.user.service.entitys;

import com.matchskills.user.service.domains.CompanyDomain;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cnpj;
    private String email;
    private String password;
    private String address;

    public CompanyDomain toCompanyDomain() {
        return new CompanyDomain(this.id,this.name,this.cnpj,this.email,this.address);
    }

}
