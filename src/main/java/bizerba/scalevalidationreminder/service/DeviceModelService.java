package bizerba.scalevalidationreminder.service;

import bizerba.scalevalidationreminder.model.DeviceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface DeviceModelService {

    Page<DeviceModel> getAllDeviceModelPaginated(Pageable pageable);

    DeviceModel getDeviceModelById(Integer idDeviceModel);

    List<DeviceModel> getAllDeviceModels();

    void saveDeviceModel(DeviceModel deviceModel, Principal principal);

    void deleteDeviceModelById(Integer idDeviceModel);




}
