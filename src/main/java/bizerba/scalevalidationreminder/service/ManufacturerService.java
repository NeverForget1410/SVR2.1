package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface ManufacturerService {

    List<Manufacturer> getAllManufacturers();

    Page<Manufacturer> getAllManufacturersPaginated(Pageable pageable);

    void saveManufacturer(Manufacturer manufacturer, Principal principal);
    void deleteManufacturerById(Integer idManufacturer);

    Manufacturer getManufacturerById(Integer idManufacturer);


}
