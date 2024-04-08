package bizerba.scalevalidationreminder.repository;

import bizerba.scalevalidationreminder.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
