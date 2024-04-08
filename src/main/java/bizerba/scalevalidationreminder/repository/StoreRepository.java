package bizerba.scalevalidationreminder.repository;


import bizerba.scalevalidationreminder.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
