package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.exception.AddressNotFoundException;
import bizerba.scalevalidationreminder.model.Address;
import bizerba.scalevalidationreminder.repository.AddressRepository;
import bizerba.scalevalidationreminder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;


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
                address.setActive(1);
            } else {
                Address existingAddress = getAddressById(address.getIdAddress());
                    address.setAddedId(existingAddress.getAddedId());
                    address.setDateAddition(existingAddress.getDateAddition());
                    address.setActive(existingAddress.getActive());

            }
            address.setModifiedId(idUser);
            address.setDateModyfication(new Date());
        });
        this.addressRepository.save(address);
    }

    @Override
    public void deleteAddressById(Integer idAddress) {
        this.addressRepository.deleteById(idAddress);
    }

    @Override
    public Address getAddressById(Integer idAddress) {
      return addressRepository.findById(idAddress)
              .orElseThrow(()-> new AddressNotFoundException("Nie znaleziono takiego adresu po ID: " + idAddress));
    }


}
