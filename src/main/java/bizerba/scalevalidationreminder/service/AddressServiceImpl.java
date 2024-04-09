package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.Address;
import bizerba.scalevalidationreminder.repository.AddressRepository;
import bizerba.scalevalidationreminder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;


@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Address> getAllAddressPaginated(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    @Override
    public void saveAddress(Address address, Principal principal) {
        String userLogin = principal.getName();
        userRepository.findByUserLogin(userLogin).ifPresent(user -> {
            Integer idUser = user.getIdUser();
            if (address.getIdAddress() == null){
                address.setAddedId(idUser);
                address.setDateAddition(new Date());
            }
            address.setModifiedId(idUser);
            address.setDateModyfication(new Date());
        });
        this.addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Address address) {
        this.addressRepository.delete(address);
    }


}
