package com.matchskills.user.service.repositorys;

import com.matchskills.user.service.entitys.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    Boolean existsByCnpj(String cnpj);

    Optional<CompanyEntity> findByCnpj(String cnpj);

}
