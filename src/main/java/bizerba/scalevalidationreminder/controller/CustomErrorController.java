package bizerba.scalevalidationreminder.controller;


import org.springframework.ui.Model;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("errorMessage", "Brak dostępu. Nie masz uprawnień do tej strony.");
                model.addAttribute("errorCode", "403 Forbidden");
                return "errorPage"; // Nazwa Twojego widoku dla błędów
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorMessage", "Nie znaleziono strony.");
                model.addAttribute("errorCode", "404 Not Found");
                return "errorPage";
            } // Możesz dodać więcej przypadków dla różnych statusów HTTP.
        }

        model.addAttribute("errorMessage", "Wystąpił nieoczekiwany błąd.");
        model.addAttribute("errorCode", "Error");
        return "errorPage"; // Domyślny widok w przypadku nieznanych błędów
    }



}
