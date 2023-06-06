package NaucimSeApp.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ukazat")
public class UkazatController {


    @GetMapping
    public String nactiMainUI(HttpSession session, Model model) {

        String okruh = (String) session.getAttribute("okruh");
        model.addAttribute("okruh", okruh);

        return "ukazat.html";
    }
}
