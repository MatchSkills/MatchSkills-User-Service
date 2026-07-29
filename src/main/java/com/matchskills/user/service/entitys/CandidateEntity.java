package com.matchskills.user.service.entitys;

import com.matchskills.user.service.domains.CandidateDomain;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "candidates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String number;

    public CandidateEntity(String name, String email, String password, String number){
        this.name = name;
        this.email = email;
        this.password = password;
        this.number = number;
    }

    public CandidateDomain toCandidateDomain() {
        return new CandidateDomain(id, name, email, number);
    }

}
