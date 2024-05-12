package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.Manufacturer;
import bizerba.scalevalidationreminder.repository.ManufacturerRepository;
import bizerba.scalevalidationreminder.repository.UserRepository;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
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
    public Manufacturer getManufacturerById(Integer idManufacturer) {
        return manufacturerRepository.findById(idManufacturer)
                .orElseThrow(()-> new RuntimeException("Manufacturer not found" + idManufacturer));
    }

    @Override
    @Transactional
    public void saveManufacturer(Manufacturer manufacturer, Principal principal) {
        String userLogin = principal.getName();
        userRepository.findByUserLogin(userLogin).ifPresent(user -> {
            Integer idUser = user.getIdUser();

            if(manufacturer.getIdManufacturer() == null){
                manufacturer.setAddedId(idUser);
                manufacturer.setDateAddition(new Date());
                manufacturer.setActive(1);
            } else {
                Manufacturer existingManufacturer = getManufacturerById(manufacturer.getIdManufacturer());
                manufacturer.setAddedId(existingManufacturer.getAddedId());
                manufacturer.setDateAddition(existingManufacturer.getDateAddition());
                manufacturer.setActive(existingManufacturer.getActive());
            }
            manufacturer.setModifiedId(idUser);
            manufacturer.setDateModyfication(new Date());
        });
        this.manufacturerRepository.save(manufacturer);
    }

    @Override
    @Transactional
    public void deleteManufacturerById(Integer idManufacturer) { this.manufacturerRepository.deleteById(idManufacturer);
    }


}
