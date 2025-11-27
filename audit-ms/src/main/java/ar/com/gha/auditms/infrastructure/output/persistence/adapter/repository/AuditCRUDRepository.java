package ar.com.gha.auditms.infrastructure.output.persistence.adapter.repository;

import ar.com.gha.auditms.infrastructure.output.persistence.entity.AuditEntity;
import org.springframework.data.repository.CrudRepository;


public interface AuditCRUDRepository extends CrudRepository<AuditEntity, Integer> {
}
