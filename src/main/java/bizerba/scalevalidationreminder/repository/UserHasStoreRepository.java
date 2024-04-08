package bizerba.scalevalidationreminder.repository;


import bizerba.scalevalidationreminder.model.UserHasStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHasStoreRepository extends JpaRepository<UserHasStore, Integer> {

}
