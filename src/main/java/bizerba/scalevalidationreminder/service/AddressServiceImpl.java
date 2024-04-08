package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.Address;
import bizerba.scalevalidationreminder.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Page<Address> getAllAddressPaginated(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    @Override
    public void saveAddress(Address address) {
        this.addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Address address) {
        this.addressRepository.delete(address);
    }


}
