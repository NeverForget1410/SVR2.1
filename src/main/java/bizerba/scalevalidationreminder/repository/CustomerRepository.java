package bizerba.scalevalidationreminder.repository;


import bizerba.scalevalidationreminder.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
