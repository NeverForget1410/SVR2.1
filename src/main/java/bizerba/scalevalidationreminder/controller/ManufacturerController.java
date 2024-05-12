package bizerba.scalevalidationreminder.controller;


import bizerba.scalevalidationreminder.model.Manufacturer;
import bizerba.scalevalidationreminder.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/api/manufacturer")
public class ManufacturerController {

    @Autowired
    public ManufacturerService manufacturerService;

    @GetMapping("/manufacturerList")
    public String getAllManufacturersPaginated(Model model,
                                               @RequestParam(defaultValue = "0") int pageNo,
                                               @RequestParam(defaultValue = "10") int pageSize) {
        Page<Manufacturer> manufacturerPage = manufacturerService.getAllManufacturersPaginated(PageRequest.of(pageNo, pageSize));
        model.addAttribute("allManufacturersList", manufacturerPage.getContent());
        model.addAttribute("currentPageAddress", pageNo);
        model.addAttribute("currentSizeAddress", pageSize);
        model.addAttribute("totalPagesAddress", manufacturerPage.getTotalPages());
        model.addAttribute("totalRecordsAddress", manufacturerPage.getTotalElements());
        return "devices/manufacturerList.html";
    }

    @GetMapping("/manufacturerList/newOrEditManufacturer")
    public String newOrEditManufacturer(@RequestParam(required = false) Integer idManufacturer, Model model) {
        Manufacturer manufacturer;
        if (idManufacturer != null) {
            manufacturer = manufacturerService.getManufacturerById(idManufacturer);
        } else {
            manufacturer = new Manufacturer();
        }
        model.addAttribute("newOrEditManufacturer", manufacturer);
        return "devices/manufacturerForm.html";
    }



    @PostMapping("/saveManufacturer")
    public String saveManufacturer(@ModelAttribute("newOrEditManufacturer")Manufacturer newOrEditManufacturer, Principal principal, RedirectAttributes redirectAttributes) {

        if(newOrEditManufacturer.getIdManufacturer() == null){
            manufacturerService.saveManufacturer(newOrEditManufacturer, principal);
            redirectAttributes.addFlashAttribute("successMessage", "Producent został pomyślnie dodany.");
        } else {
            manufacturerService.saveManufacturer(newOrEditManufacturer, principal);
            redirectAttributes.addFlashAttribute("successMessage", "Producent został pomyślnie zaktualizowany.");
        }
        return "redirect:/api/manufacturer/manufacturerList";

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deleteManufacturer/{idManufacturer}")
    public String deleteManufacturer(@PathVariable(value = "idManufacturer")Integer idManufacturer, RedirectAttributes redirectAttributes) {
        this.manufacturerService.deleteManufacturerById(idManufacturer);
        redirectAttributes.addFlashAttribute("deleteMessage", "Producent został pomyślnie usunięty.");
        return "redirect:/api/manufacturer/manufacturerList";
    }



}
