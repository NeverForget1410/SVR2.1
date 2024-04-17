package bizerba.scalevalidationreminder.controller;

import bizerba.scalevalidationreminder.exception.AddressNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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



    @ExceptionHandler(AddressNotFoundException.class)
    public ModelAndView addressNotFound(AddressNotFoundException ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", ex.getMessage());
        modelAndView.setViewName("404");
        return modelAndView;
    }


    @GetMapping("/404")
    public String notFound(Model model){
        model.addAttribute("errorMessage", "The page you are looking for does not exist.");
        return "404";  // Nazwa widoku 404
    }

}
