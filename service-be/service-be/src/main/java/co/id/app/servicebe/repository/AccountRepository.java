package co.id.app.servicebe.repository;

import co.id.app.servicebe.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long>,
        JpaSpecificationExecutor<Accounts> {

    @Query(value = "select count(*) from accounts where username=:username", nativeQuery = true)
    Integer countUserByUsername(@PathVariable("username")String username);

    @Query(value = "select * from accounts where username=:username", nativeQuery = true)
    Optional<Accounts> getAccountByUsername(@PathVariable("username")String username);
}
