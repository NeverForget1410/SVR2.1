package bizerba.scalevalidationreminder.controller;

import bizerba.scalevalidationreminder.model.Devices;
import bizerba.scalevalidationreminder.repository.ManufacturerRepository;
import bizerba.scalevalidationreminder.repository.UserRepository;
import bizerba.scalevalidationreminder.repository.WeightClassRepository;
import bizerba.scalevalidationreminder.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/devices")
public class DevicesController {

    @Autowired
    public DevicesService devicesService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public WeighClassService weighClassService;

    @Autowired
    public DeviceModelService deviceModelService;

    @Autowired
    public StoreService storeService;

    @GetMapping("/deviceList")
    public String getAllDevicesForUser(Model model,
                                       @RequestParam(defaultValue = "0") int pageNo,
                                       @RequestParam(defaultValue = "10") int pageSize, Principal principal) {
        String userLogin = principal.getName();
        userRepository.findByUserLogin(userLogin).ifPresentOrElse(user -> {
            Page<Devices> devicePage = devicesService.getAllDevicesForUser(PageRequest.of(pageNo, pageSize), user.getIdUser());
            List<Integer> amountOnPage = Arrays.asList(10, 20, 50, 100, 200);
            model.addAttribute("amountOnPage", amountOnPage);
            model.addAttribute("allDevicesList", devicePage.getContent());
            model.addAttribute("currentPageDevice", pageNo);
            model.addAttribute("totalPagesDevice", devicePage.getTotalPages());
            model.addAttribute("currentSizeDevices", pageSize);
            model.addAttribute("totalRecordsDevices", devicePage.getTotalElements());
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono użytkownika.");
        });
        return "devices/devicesList.html";
    }

    //Wyświelanie modala
    @GetMapping("/deviceDetails/{idDevice}")
    @ResponseBody
    public Devices getDevicesDetails(@PathVariable(value = "idDevice") Integer idDevice, Model model) {
        Devices devices = devicesService.getDeviceById(idDevice);
        model.addAttribute("devices", devices);
        return devicesService.getDeviceById(idDevice);
    }


    @GetMapping("/newOrEditDevice")
    public String newOrEditDevice(@RequestParam(required = false) Integer idDevice,Model model) {
        Devices devices;
        if (idDevice != null) {
            devices = devicesService.getDeviceById(idDevice);
        } else {
            devices = new Devices();
        }
        model.addAttribute("newOrEditDevice", devices);
        model.addAttribute("weightClassList", weighClassService.getAllWeightClasses());
        model.addAttribute("deviceModelList", deviceModelService.getAllDeviceModels());
        model.addAttribute("storeList", storeService.getAllStores());
        return "devices/devicesForm.html";
    }


    @PostMapping("/saveDevice")
    public String saveDevices(@ModelAttribute("newOrEditDevice") Devices newOrEditDevice, Principal principal, RedirectAttributes redirectAttributes) {
        if (newOrEditDevice.getIdDevice() == null) {
            devicesService.saveDevice(newOrEditDevice, principal);
            redirectAttributes.addFlashAttribute("successMessage", "Miasto zostało pomyślnie dodane.");
        } else {
            devicesService.saveDevice(newOrEditDevice, principal);
            redirectAttributes.addFlashAttribute("successMessage", "Miasto zostało pomyślnie zaktualizowane.");
        }
        return "redirect:/api/devices/deviceList";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteDevice/{idDevice}")
    public String deleteDevice(@PathVariable(value = "idDevice") Integer idDevice, Principal principal, RedirectAttributes redirectAttributes) {
        this.devicesService.deleteDeviceById(idDevice);
        redirectAttributes.addFlashAttribute("deleteMessage", "Urządzenie o ID: " + idDevice +  " zostało pomyślnie usunięte.");
        return "redirect:/api/devices/deviceList";
    }


}
