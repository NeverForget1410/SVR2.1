package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.Devices;
import bizerba.scalevalidationreminder.repository.DevicesRepository;
import bizerba.scalevalidationreminder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DevicesServiceImpl implements DevicesService{

    private final DevicesRepository devicesRepository;
    private final UserRepository userRepository;

    public DevicesServiceImpl(DevicesRepository devicesRepository, UserRepository userRepository) {
        this.devicesRepository = devicesRepository;
        this.userRepository = userRepository;
    }




    @Override
    public Page<Devices> getAllDevicesForUser(Pageable pageable, Integer idUser) {
        return devicesRepository.getAllDevicesForUser(pageable, idUser);
    }


    @Override
    public Devices getDeviceById(Integer idDevice) {
        return devicesRepository.findById(idDevice).orElseThrow(()-> new RuntimeException("Device not found" + idDevice));
    }

    @Override
    public void saveDevice(Devices device, Principal principal) {
        String userLogin = principal.getName();
        userRepository.findByUserLogin(userLogin).ifPresent(user -> {
            Integer idUser = user.getIdUser();

            if(device.getIdDevice() == null){
                device.setAddedId(idUser);
                device.setDateAddition(new Date());
                device.setActive(1);
            } else {
                Devices existingDevice = getDeviceById(device.getIdDevice());
                device.setAddedId(existingDevice.getAddedId());
                device.setDateAddition(existingDevice.getDateAddition());
                device.setActive(existingDevice.getActive());
            }
            device.setModifiedId(idUser);
            device.setDateModyfication(new Date());
        });

            this.devicesRepository.save(device);
    }

    @Override
    public void deleteDeviceById(Integer idDevice) {
        this.devicesRepository.deleteById(idDevice);
    }

}
