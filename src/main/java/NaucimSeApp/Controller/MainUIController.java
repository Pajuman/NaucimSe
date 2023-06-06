package NaucimSeApp.Controller;

import NaucimSeApp.Model.Slovo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainUIController {

    @GetMapping
    public String nactiMainUI() {
        return "mainUI.html";
    }

    @PostMapping("/submit")
    public String prijmiAkci(@RequestParam("okruh_selection") String okruh, @RequestParam("akce_selection") String akce, HttpSession session) {

        session.setAttribute("okruh", okruh);

        String resultPage;

        if(akce.equals("vyzkouset_akce")){
            resultPage = "redirect:/vyzkouset";
        } else {resultPage = "redirect:/ukazat";}

        return resultPage;

    }
}
