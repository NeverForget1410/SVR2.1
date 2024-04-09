package bizerba.scalevalidationreminder.controller;

import bizerba.scalevalidationreminder.model.Address;
import bizerba.scalevalidationreminder.model.City;
import bizerba.scalevalidationreminder.repository.AddressRepository;
import bizerba.scalevalidationreminder.service.AddressService;
import bizerba.scalevalidationreminder.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class LocationsController {

    @Autowired
    public AddressService addressService;

    @Autowired
    public CityService cityService;


    @GetMapping("/address")
    public String getAllAddressPaginated(Model model, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
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
        Address newAddress = new Address();
        model.addAttribute("newAddress", newAddress);
        model.addAttribute("cityList", cityService.getAllCity());
        return "locations/address_new.html";
    }
    @PostMapping("/saveAddress")
    public String saveAddress(@ModelAttribute("newAddress") Address newAddress, Principal principal) {
        addressService.saveAddress(newAddress, principal);
        return "redirect:/address";
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


}
