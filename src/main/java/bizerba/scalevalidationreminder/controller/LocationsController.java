package bizerba.scalevalidationreminder.controller;

import bizerba.scalevalidationreminder.model.Address;
import bizerba.scalevalidationreminder.model.City;
import bizerba.scalevalidationreminder.service.AddressService;
import bizerba.scalevalidationreminder.service.CityService;
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
@RequestMapping("/api/locations")
public class LocationsController {

    @Autowired
    public AddressService addressService;

    @Autowired
    public CityService cityService;


    @GetMapping("/addressList")
    public String getAllAddressPaginated(Model model,
                                         @RequestParam(defaultValue = "0") int pageNo,
                                         @RequestParam(defaultValue = "10") int pageSize) {
        Page<Address> addressPage = addressService.getAllAddressPaginated(PageRequest.of(pageNo,pageSize));
        List<Integer> amountOnPage = Arrays.asList(10, 20, 50, 100, 200);
        model.addAttribute("amountOnPage", amountOnPage);
        model.addAttribute("allAddressList", addressPage.getContent());
        model.addAttribute("currentPageAddress", pageNo);
        model.addAttribute("currentSizeAddress", pageSize);
        model.addAttribute("totalPagesAddress", addressPage.getTotalPages());
        model.addAttribute("totalRecordsAddress", addressPage.getTotalElements());
        return "locations/addressList.html";
    }

    @GetMapping("/addressList/newOrEditAddress")
    public String newOrEditAddress(@RequestParam(required = false) Integer idAddress, Model model) {
        Address address;
        if (idAddress != null) {
            address = addressService.getAddressById(idAddress);
        } else {
            address = new Address();
        }
        model.addAttribute("newAddress", address);
        model.addAttribute("cityList", cityService.getAllCity());
        return "locations/addressForm.html";
    }


    @PostMapping("/saveAddress")
    public String saveAddress(@ModelAttribute("newAddress") Address newAddress, Principal principal, RedirectAttributes redirectAttributes) {
        if (newAddress.getIdAddress() == null) {
            addressService.saveAddress(newAddress, principal);
            redirectAttributes.addFlashAttribute("successMessage", "Adres został pomyślnie dodany.");
        } else {
            addressService.saveAddress(newAddress, principal);
            redirectAttributes.addFlashAttribute("successMessage", "Adres został pomyślnie zaktualizowany.");
        }
        return "redirect:/api/locations/addressList";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteAddress/{idAddress}")
    public String deleteAddress(@PathVariable(value = "idAddress") Integer idAddress, RedirectAttributes redirectAttributes) {
        this.addressService.deleteAddressById(idAddress);
        redirectAttributes.addFlashAttribute("deleteMessage", "Adres został pomyślnie usunięty.");
        return "redirect:/api/locations/addressList";
    }




    @GetMapping("/cityList")
    public String getAllCityPagable(Model model, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        Page<City> cityPage = cityService.getAllCityPaginated(PageRequest.of(pageNo, pageSize));
        List<Integer> amountOnPage = Arrays.asList(10, 20, 50, 100, 200);
        model.addAttribute("amountOnPage", amountOnPage);
        model.addAttribute("allCityList", cityPage.getContent());
        model.addAttribute("currentPageCity", pageNo);
        model.addAttribute("currentSizeCity", pageSize);
        model.addAttribute("totalPagesCity", cityPage.getTotalPages());
        model.addAttribute("totalRecordsCity", cityPage.getTotalElements());
        return "locations/cityList.html";
    }

    @GetMapping("/cityList/newOrEditCity")
    public String newOrEditCity(@RequestParam(required = false) Integer idCity, Model model) {
        City city;
        if (idCity != null) {
            city = cityService.getCityById(idCity);
        } else {
            city = new City();
        }
        model.addAttribute("newCity", city);
        return "locations/cityForm.html";
    }

    @PostMapping("/saveCity")
    public String saveCity(@ModelAttribute("newCity") City newCity, Principal principal, RedirectAttributes redirectAttributes) {
        if (newCity.getIdCity() == null) {
            cityService.saveCity(newCity, principal);
            redirectAttributes.addFlashAttribute("successMessage", "Miasto zostało pomyślnie dodane.");
        } else {
            cityService.saveCity(newCity, principal);
            redirectAttributes.addFlashAttribute("successMessage", "Miasto zostało pomyślnie zaktualizowane.");
        }
        return "redirect:/api/locations/cityList";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteCity/{idCity}")
    public String deleteCity(@PathVariable(value = "idCity") Integer idCity, RedirectAttributes redirectAttributes) {
        this.cityService.deleteCityById(idCity);
        redirectAttributes.addFlashAttribute("deleteMessage", "Miasto o numerze " + idCity +  " zostało pomyślnie usunięte.");
        return "redirect:/api/locations/cityList";
    }

}
