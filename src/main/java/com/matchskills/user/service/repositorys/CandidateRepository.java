package com.matchskills.user.service.repositorys;

import com.matchskills.user.service.entitys.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {

    Boolean existsByEmail(String email);

    Optional<CandidateEntity> findByEmail(String email);

}
