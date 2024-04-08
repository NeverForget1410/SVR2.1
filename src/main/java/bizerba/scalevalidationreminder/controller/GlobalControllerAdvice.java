package bizerba.scalevalidationreminder.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("userName")
    public String addUserName(Principal principal){
        if (principal != null) {
            return principal.getName();
        }
        return null;
    }




//    @GetMapping"/404"
//    public String notFound(){
//        return "/404.html";
//    }

}
