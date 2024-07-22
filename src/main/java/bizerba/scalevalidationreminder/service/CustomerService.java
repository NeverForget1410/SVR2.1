package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;

public interface CustomerService {

    Page<Customer> getAllCustomerPaginated(Pageable pageable);

    void saveCustomer(Customer customer, Principal principal);
    void deleteCustomer(Integer idCustomer);

    Customer getCustomerById(Integer idCustomer);

}
