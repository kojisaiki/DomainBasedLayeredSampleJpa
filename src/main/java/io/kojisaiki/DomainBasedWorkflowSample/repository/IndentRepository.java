package io.kojisaiki.DomainBasedWorkflowSample.repository;

import io.kojisaiki.DomainBasedWorkflowSample.entity.Indent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IndentRepository extends JpaRepository<Indent, Long> {
}
