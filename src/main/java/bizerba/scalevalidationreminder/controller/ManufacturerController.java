package bizerba.scalevalidationreminder.controller;


import bizerba.scalevalidationreminder.model.Manufacturer;
import bizerba.scalevalidationreminder.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/manufacturer")
public class ManufacturerController {

    @Autowired
    public ManufacturerService manufacturerService;

    @GetMapping("/list")
    public String getAllManufacturersPaginated(Model model, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        Page<Manufacturer> manufacturerPage = manufacturerService.getAllManufacturersPaginated(PageRequest.of(pageNo, pageSize));
        model.addAttribute("allManufacturersList", manufacturerPage.getContent());
        model.addAttribute("currentPageAddress", pageNo);
        model.addAttribute("currentSizeAddress", pageSize);
        model.addAttribute("totalPagesAddress", manufacturerPage.getTotalPages());
        model.addAttribute("totalRecordsAddress", manufacturerPage.getTotalElements());
        return "devices/manufacturer_list.html";
    }



}
