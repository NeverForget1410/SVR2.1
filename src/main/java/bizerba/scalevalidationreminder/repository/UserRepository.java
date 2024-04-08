package bizerba.scalevalidationreminder.repository;


import bizerba.scalevalidationreminder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserLogin(String userLogin);

}
