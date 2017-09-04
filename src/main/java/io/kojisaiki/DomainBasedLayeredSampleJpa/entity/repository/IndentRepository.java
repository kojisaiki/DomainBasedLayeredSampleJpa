package io.kojisaiki.DomainBasedLayeredSampleJpa.entity.repository;

import io.kojisaiki.DomainBasedLayeredSampleJpa.entity.Indent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IndentRepository extends JpaRepository<Indent, Long> {
}
