package co.id.app.servicebe.repository;

import co.id.app.servicebe.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long>,
        JpaSpecificationExecutor<Roles> {
}
