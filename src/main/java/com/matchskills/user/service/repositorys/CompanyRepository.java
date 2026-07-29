package com.matchskills.user.service.repositorys;

import com.matchskills.user.service.entitys.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    Boolean existsByCnpj(String cnpj);

}
