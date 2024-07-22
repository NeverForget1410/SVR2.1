package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;


public interface AddressService {



   Page<Address> getAllAddressPaginated(Pageable pageable);

   List<Address> getAllAddress();

   void saveAddress(Address address, Principal principal);

   void deleteAddressById(Integer idAddress);

   Address getAddressById(Integer idAddress);
}
