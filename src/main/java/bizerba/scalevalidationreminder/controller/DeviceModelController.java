package bizerba.scalevalidationreminder.controller;


import bizerba.scalevalidationreminder.model.DeviceModel;
import bizerba.scalevalidationreminder.service.DeviceModelService;
import bizerba.scalevalidationreminder.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/devices")
public class DeviceModelController {

    @Autowired
    public DeviceModelService deviceModelService;

    @Autowired
    public ManufacturerService manufacturerService;

    @GetMapping("/deviceModelList")
    public String getAllDeviceModelPaginated(Model model,
                                             @RequestParam(defaultValue = "0")int pageNo,
                                             @RequestParam(defaultValue = "10")int pageSize){
        Page<DeviceModel> deviceModelPage = deviceModelService.getAllDeviceModelPaginated(PageRequest.of(pageNo, pageSize));
        List<Integer> amountOnPage = Arrays.asList(10, 20, 50, 100, 200);
        model.addAttribute("amountOnPage", amountOnPage);
        model.addAttribute("allDeviceModelList", deviceModelPage.getContent());
        model.addAttribute("currentPageDeviceModel", pageNo);
        model.addAttribute("currentSizeDeviceModel", pageSize);
        model.addAttribute("totalPagesDeviceModel", deviceModelPage.getTotalPages());
        model.addAttribute("totalRecordsDeviceModel", deviceModelPage.getTotalElements());
        return "devices/deviceModelList.html";
    }


    @GetMapping("/deviceModelList/newOrEditDeviceModel")
    public String newOrEditDeviceModel(@RequestParam(required = false) Integer idDeviceModel, Model model){
        DeviceModel deviceModel;
        if (idDeviceModel != null) {
            deviceModel = deviceModelService.getDeviceModelById(idDeviceModel);
        } else {
            deviceModel = new DeviceModel();
        }
        model.addAttribute("newOrEditDeviceModel", deviceModel);
        model.addAttribute("manufacturerList", manufacturerService.getAllManufacturers());
        return "devices/deviceModelForm.html";
    }

    @PostMapping("/saveDeviceModel")
    public String saveDeviceModel(@ModelAttribute("newOrEditDeviceModel") DeviceModel newOrEditDeviceModel, Principal principal, RedirectAttributes redirectAttributes){
        if (newOrEditDeviceModel.getIdDeviceModel() == null){
            deviceModelService.saveDeviceModel(newOrEditDeviceModel, principal);
            redirectAttributes.addFlashAttribute("successMessage", "Model urządzenia został pomyślnie dodany.");
        } else {
            deviceModelService.saveDeviceModel(newOrEditDeviceModel, principal);
            redirectAttributes.addFlashAttribute("successMessage", "Model urządzenia został pomyślnie zaktualizowany.");
        }
        return "redirect:/api/devices/deviceModelList";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteDeviceModel/{idDeviceModel}")
    public String deleteDeviceModel(@PathVariable(value = "idDeviceModel") Integer idDeviceModel, Principal principal, RedirectAttributes redirectAttributes){
        this.deviceModelService.deleteDeviceModelById(idDeviceModel);
        redirectAttributes.addFlashAttribute("deleteMessage", "Model o ID: " + idDeviceModel +  " został pomyślnie usunięty.");
        return "redirect:/api/devices/deviceModelList";
    }


}
