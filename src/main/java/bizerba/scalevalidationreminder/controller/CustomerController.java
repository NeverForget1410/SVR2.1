package bizerba.scalevalidationreminder.controller;


import bizerba.scalevalidationreminder.model.Customer;
import bizerba.scalevalidationreminder.repository.AddressRepository;
import bizerba.scalevalidationreminder.service.AddressService;
import bizerba.scalevalidationreminder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

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
    private AddressRepository addressRepository;

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
        model.addAttribute("addressList", addressService.getAllAddress());
        return "customer/customerForm.html";
    }


}
