package bizerba.scalevalidationreminder.controller;


import bizerba.scalevalidationreminder.model.Customer;
import bizerba.scalevalidationreminder.repository.AddressRepository;
import bizerba.scalevalidationreminder.service.AddressService;
import bizerba.scalevalidationreminder.service.CityService;
import bizerba.scalevalidationreminder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    public CustomerService customerService;

    @Autowired
    public AddressService addressService;

    @Autowired
    public CityService cityService;

    @GetMapping("/customerList")
    public String getAllCustomerPaginated(Model model,
                                          @RequestParam(defaultValue = "0") int pageNo,
                                          @RequestParam(defaultValue = "10") int pageSize){
        Page<Customer> customerPage = customerService.getAllCustomerPaginated(PageRequest.of(pageNo, pageSize));
        List<Integer> amountOnPage = Arrays.asList(10, 20, 50, 100, 200);
        model.addAttribute("amountOnPage", amountOnPage);
        model.addAttribute("allCustomerList", customerPage.getContent());
        model.addAttribute("currentPageCustomer", pageNo);
        model.addAttribute("currentSizeCustomer", pageSize);
        model.addAttribute("totalPagesCustomer", customerPage.getTotalPages());
        model.addAttribute("totalRecordsCustomer", customerPage.getTotalElements());
        return "customer/customerList.html";
    }

    @GetMapping("/customerList/newOrEditCustomer")
    public String newOrEditCustomer(@RequestParam(required = false) Integer idCustomer, Model model){
        Customer customer;
        if (idCustomer != null) {
            customer = customerService.getCustomerById(idCustomer);
        } else {
            customer = new Customer();
        }
        model.addAttribute("newOrEditCustomer", customer);
        model.addAttribute("addressListForCustomer", addressService.getAllAddress());
        return "customer/customerForm.html";
    }

   @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("newOrEditCustomer") Customer newOrEditCustomer, Principal principal, RedirectAttributes redirectAttributes){
        if (newOrEditCustomer.getIdCustomer() == null){
            customerService.saveCustomer(newOrEditCustomer, principal);
            redirectAttributes.addFlashAttribute("successMessage", "Klient został pomyślnie dodany.");
        } else {
            customerService.saveCustomer(newOrEditCustomer, principal);
            redirectAttributes.addFlashAttribute("successMessage", "Klient został pomyślnie zaktualizowany.");
        }
        return "redirect:/api/customer/customerList";
   }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteeCustomer/{idCustomer}")
    public String deleteCustomerById(@PathVariable(value = "idCustomer") Integer idCustomer, RedirectAttributes redirectAttributes){
        this.customerService.deleteCustomerById(idCustomer);
        redirectAttributes.addFlashAttribute("deleteMessage", "klient o numerze " + idCustomer +  " został pomyślnie usunięty.");
        return "redirect:/api/customer/customerList";
    }
}
