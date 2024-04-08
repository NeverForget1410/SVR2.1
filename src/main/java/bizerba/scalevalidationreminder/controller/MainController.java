package bizerba.scalevalidationreminder.controller;

import bizerba.scalevalidationreminder.model.Address;
import bizerba.scalevalidationreminder.model.City;
import bizerba.scalevalidationreminder.model.Devices;
import bizerba.scalevalidationreminder.model.User;
import bizerba.scalevalidationreminder.repository.UserRepository;
import bizerba.scalevalidationreminder.service.AddressService;
import bizerba.scalevalidationreminder.service.AddressServiceImpl;
import bizerba.scalevalidationreminder.service.CityService;
import bizerba.scalevalidationreminder.service.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    public AddressService addressService;

    @Autowired
    public CityService cityService;

    @Autowired
    public DevicesService devicesService;

    @Autowired
    public UserRepository userRepository;


    @GetMapping("/")
    public String getIndex(Model model) {
        return "/index.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Niepoprawny login lub hasło");
        }
        return "/login.html";
    }

    @GetMapping("/logout")
    public String logout() {
        return "/logout.html";
    }


    @GetMapping("/address")
    public String getAllAddressPaginated(Model model,@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        Page<Address> addressPage = addressService.getAllAddressPaginated(PageRequest.of(pageNo,pageSize));
        model.addAttribute("allAddressList", addressPage.getContent());
        model.addAttribute("currentPageAddress", pageNo);
        model.addAttribute("currentSizeAddress", pageSize);
        model.addAttribute("totalPagesAddress", addressPage.getTotalPages());
        model.addAttribute("totalRecordsAddress", addressPage.getTotalElements());
        return "locations/address_list.html";
    }

    @GetMapping("/address/newAddress")
    public String newAddress(Model model) {
        Address address = new Address();

        model.addAttribute("newAddress", address);
        model.addAttribute("cityList", cityService.getAllCity());
        return "locations/address_new.html";
    }


    @GetMapping("/city")
    public String getAllCityPagable(Model model, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        Page<City> cityPage = cityService.getAllCityPaginated(PageRequest.of(pageNo, pageSize));
        model.addAttribute("allCityList", cityPage.getContent());
        model.addAttribute("currentPageCity", pageNo);
        model.addAttribute("currentSizeCity", pageSize);
        model.addAttribute("totalPagesCity", cityPage.getTotalPages());
        model.addAttribute("totalRecordsCity", cityPage.getTotalElements());
        return "locations/city_list.html";
    }


    ///   --------------------------------------------------------------------------------------------------------------------

    //Lista  urządzeń
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
