package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.Devices;
import bizerba.scalevalidationreminder.repository.DevicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevicesServiceImpl implements DevicesService{

    @Autowired
    private DevicesRepository devicesRepository;

    @Override
    public Page<Devices> getAllDevicesForUser(Pageable pageable, Integer idUser) {
        return devicesRepository.getAllDevicesForUser(pageable, idUser);
    }


    @Override
    public Devices getDeviceById(Integer idDevice) {
        Optional<Devices> optionalDevices = devicesRepository.findById(idDevice);
        Devices devices = null;
        if(optionalDevices.isPresent()){
            devices = optionalDevices.get();
        } else {
            throw new RuntimeException("Nie znaleziono urządzenia wyszukując po ID " + idDevice);
        }
        return devices;
    }

}
