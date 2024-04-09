package bizerba.scalevalidationreminder.controller;

import bizerba.scalevalidationreminder.model.Address;
import bizerba.scalevalidationreminder.model.City;
import bizerba.scalevalidationreminder.model.Devices;
import bizerba.scalevalidationreminder.model.User;
import bizerba.scalevalidationreminder.repository.AddressRepository;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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



}
