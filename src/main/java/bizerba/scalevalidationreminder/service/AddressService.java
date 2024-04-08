package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService {



   Page<Address> getAllAddressPaginated(Pageable pageable);

   void saveAddress(Address address);
   void deleteAddress(Address address);
}
