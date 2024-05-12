package bizerba.scalevalidationreminder.controller;


import bizerba.scalevalidationreminder.model.DeviceModel;
import bizerba.scalevalidationreminder.service.DeviceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/devices")
public class DeviceModelController {

    @Autowired
    public DeviceModelService deviceModelService;


    @GetMapping("/deviceModelList")
    public String getAllDeviceModelPaginated(Model model,
                                             @RequestParam(defaultValue = "0")int pageNo,
                                             @RequestParam(defaultValue = "10")int pageSize){
        Page<DeviceModel> deviceModelPage = deviceModelService.getAllDeviceModelPaginated(PageRequest.of(pageNo, pageSize));
        model.addAttribute("allDeviceModelList", deviceModelPage.getContent());
        model.addAttribute("currentPageDeviceModel", pageNo);
        model.addAttribute("currentSizeDeviceModel", pageSize);
        model.addAttribute("totalPagesDeviceModel", deviceModelPage.getTotalPages());
        model.addAttribute("totalRecordsDeviceModel", deviceModelPage.getTotalElements());
        return "devices/deviceModelList.html";
    }



}
