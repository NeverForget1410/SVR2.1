package bizerba.scalevalidationreminder.controller;

import bizerba.scalevalidationreminder.model.Devices;
import bizerba.scalevalidationreminder.repository.ManufacturerRepository;
import bizerba.scalevalidationreminder.repository.UserRepository;
import bizerba.scalevalidationreminder.service.DevicesService;
import bizerba.scalevalidationreminder.service.ManufacturerService;
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

@Controller
@RequestMapping("/api/devices")
public class DevicesController {

    @Autowired
    public DevicesService devicesService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ManufacturerService manufacturerService;

    @GetMapping("/list")
    public String getAllDevicesForUser(Model model,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size, Principal principal) {
        String userLogin = principal.getName();
        userRepository.findByUserLogin(userLogin).ifPresentOrElse(user -> {
            Page<Devices> devicePage = devicesService.getAllDevicesForUser(PageRequest.of(page, size), user.getIdUser());
            model.addAttribute("allDevicesList", devicePage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", devicePage.getTotalPages());
            model.addAttribute("currentSize", size);
            model.addAttribute("totalRecords", devicePage.getTotalElements());
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono użytkownika.");
        });
        return "devices/devices_list.html";
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
        model.addAttribute("manufacturerList", manufacturerService.getAllManufacturers());
        return "devices/devices_forms.html";
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
        return "redirect:/devices";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteDevice/{idDevice}")
    public String deleteDevice(@PathVariable(value = "idDevice") Integer idDevice, Principal principal, RedirectAttributes redirectAttributes) {
        this.devicesService.deleteDeviceById(idDevice);
        redirectAttributes.addFlashAttribute("deleteMessage", "Urządzenie o ID: " + idDevice +  " zostało pomyślnie usunięte.");
        return "redirect:/devices";
    }


}
