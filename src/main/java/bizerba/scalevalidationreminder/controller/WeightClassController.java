package bizerba.scalevalidationreminder.controller;


import bizerba.scalevalidationreminder.model.WeightClass;
import bizerba.scalevalidationreminder.service.WeighClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/devices")
public class WeightClassController {

    @Autowired
    public WeighClassService weightClassService;

    @GetMapping("/weightClassList")
    public String getAllWeightClassPaginated(Model model,
                                             @RequestParam(defaultValue = "0")int pageNo,
                                             @RequestParam(defaultValue = "10") int pageSize){
        Page<WeightClass> weightClassPage = weightClassService.getAllWeightClassPaginated(PageRequest.of(pageNo, pageSize));
        model.addAttribute("allWeightClassList", weightClassPage.getContent());
        model.addAttribute("currentPageWeightClass", pageNo);
        model.addAttribute("currentSizeWeightClass", pageSize);
        model.addAttribute("totalPagesWeightClass", weightClassPage.getTotalPages());
        model.addAttribute("totalRecordsWeightClass", weightClassPage.getTotalElements());
        return "devices/weightClassList.html";
    }




}
