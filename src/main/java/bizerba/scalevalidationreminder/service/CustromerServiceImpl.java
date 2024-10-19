package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.Customer;
import bizerba.scalevalidationreminder.repository.CustomerRepository;
import bizerba.scalevalidationreminder.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;

@Service
public class CustromerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public CustromerServiceImpl(CustomerRepository customerRepository, UserRepository userRepository){
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Page<Customer> getAllCustomerPaginated(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getCustomerById(Integer idCustomer) {
        return customerRepository.findById(idCustomer)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono klienta"));
    }


    @Override
    @Transactional
    public void saveCustomer(Customer customer, Principal principal) {
        String userLogin = principal.getName();
        userRepository.findByUserLogin(userLogin).ifPresent(user -> {
            Integer idUser = user.getIdUser();
            if (customer.getIdCustomer() == null){
                customer.setAddedId(idUser);
                customer.setDateAddition(new Date());
                customer.setActive(1);
            } else {
                Customer existingCustomer = getCustomerById(customer.getIdCustomer());
                customer.setAddedId(existingCustomer.getIdCustomer());
                customer.setDateAddition(existingCustomer.getDateAddition());
                customer.setActive(existingCustomer.getActive());
            }
            customer.setModifiedId(idUser);
            customer.setDateModyfication(new Date());
        });
        this.customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void deleteCustomerById(Integer idCustomer) {
        this.customerRepository.deleteById(idCustomer);
    }


}
