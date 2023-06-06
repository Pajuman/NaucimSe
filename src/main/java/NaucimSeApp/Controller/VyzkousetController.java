package NaucimSeApp.Controller;

import NaucimSeApp.Model.SlovnikServlet;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/vyzkouset")
public class VyzkousetController {

    SlovnikServlet slovnikServlet;


    @GetMapping
    public String nacti(HttpSession session, Model model) {
        String okruh = (String) session.getAttribute("okruh");
        model.addAttribute("okruh", okruh);

        return "vyzkouset.html";
    }

    @PostMapping
    public String odpoved(@RequestParam("znalost") String odpoved){
        if(odpoved.equals("vim")){

        }
        else{}

        return "vyzkouset.html";
    }

}