package bizerba.scalevalidationreminder.repository;


import bizerba.scalevalidationreminder.model.UserHasRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserHasRoleRepository extends JpaRepository<UserHasRole, Integer> {
}
