package bizerba.scalevalidationreminder.controller;

import bizerba.scalevalidationreminder.exception.AddressNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> addressNotFound(AddressNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


//    @GetMapping"/404"
//    public String notFound(){
//        return "/404.html";
//    }

}
