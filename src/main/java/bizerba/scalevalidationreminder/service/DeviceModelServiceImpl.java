package bizerba.scalevalidationreminder.service;


import bizerba.scalevalidationreminder.model.DeviceModel;
import bizerba.scalevalidationreminder.repository.DeviceModelRepository;
import bizerba.scalevalidationreminder.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class DeviceModelServiceImpl implements DeviceModelService {

    private final DeviceModelRepository deviceModelRepository;
    private final UserRepository userRepository;

    public DeviceModelServiceImpl(DeviceModelRepository deviceModelRepository, UserRepository userRepository) {
        this.deviceModelRepository = deviceModelRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Page<DeviceModel> getAllDeviceModelPaginated(Pageable pageable) {
        return deviceModelRepository.findAll(pageable);
    }

    @Override
    public DeviceModel getDeviceModelById(Integer idDeviceModel) {
        return deviceModelRepository.findById(idDeviceModel)
                .orElseThrow(() -> new RuntimeException("Device Model Not Found" + idDeviceModel));
    }

    @Override
    public List<DeviceModel> getAllDeviceModels() {
        return deviceModelRepository.findAll();
    }

    @Override
    @Transactional
    public void saveDeviceModel(DeviceModel deviceModel, Principal principal) {

        String userLogin = principal.getName();
        userRepository.findByUserLogin(userLogin).ifPresent(user -> {
            Integer idUser = user.getIdUser();

            if(deviceModel.getIdDeviceModel() == null){
                deviceModel.setAddedId(idUser);
                deviceModel.setDateAddition(new Date());
                deviceModel.setActive(1);
            } else {
                DeviceModel existingDeviceModel = getDeviceModelById(deviceModel.getIdDeviceModel());
                deviceModel.setAddedId(existingDeviceModel.getIdDeviceModel());
                deviceModel.setDateAddition(existingDeviceModel.getDateAddition());
                deviceModel.setActive(existingDeviceModel.getActive());
            }
            deviceModel.setModifiedId(idUser);
            deviceModel.setDateModyfication(new Date());
        });
        this.deviceModelRepository.save(deviceModel);
    }

    @Override
    @Transactional
    public void deleteDeviceModelById(Integer idDeviceModel) {
        this.deviceModelRepository.deleteById(idDeviceModel);
    }


}
