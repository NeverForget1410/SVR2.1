package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.Manufacturer;
import bizerba.scalevalidationreminder.repository.ManufacturerRepository;
import bizerba.scalevalidationreminder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final
    ManufacturerRepository manufacturerRepository;
    UserRepository userRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, UserRepository userRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.userRepository = userRepository;
    }




    @Override
    public List<Manufacturer> getAllManufacturers() {
     return manufacturerRepository.findAll();
    }

    @Override
    public Page<Manufacturer> getAllManufacturersPaginated(Pageable pageable) {
        return manufacturerRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void saveManufacturer(Manufacturer manufacturer, Principal principal) {

    }

    @Override
    @Transactional
    public void deleteManufacturerById(Integer idManufacturer) {

    }
}
