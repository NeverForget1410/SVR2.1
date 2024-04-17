package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.Devices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface DevicesService {

    //Używane do wyświetlania dodatkowych informacji w modal
    Devices getDeviceById(Integer idDevice);


    //Wyświetlanie listy urządzeń dla danego użytkownika z podziałem na strony
    Page<Devices> getAllDevicesForUser(Pageable pageable, Integer idUser);

    void saveDevice(Devices device, Principal principal);
    void deleteDeviceById(Integer idDevice);



}
