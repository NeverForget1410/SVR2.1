package bizerba.scalevalidationreminder.controller;

import bizerba.scalevalidationreminder.model.Devices;
import bizerba.scalevalidationreminder.repository.UserRepository;
import bizerba.scalevalidationreminder.service.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
public class DevicesController {

    @Autowired
    public DevicesService devicesService;

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/devices")
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
}
