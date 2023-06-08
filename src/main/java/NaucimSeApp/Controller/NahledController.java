package NaucimSeApp.Controller;

import NaucimSeApp.Model.Slovo;
import NaucimSeApp.Model.Tabulka;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/ukazat")
public class NahledController {
@Autowired
    Tabulka tabulka;

    @GetMapping
    public String nactiMainUI(HttpSession session, Model model) throws JsonProcessingException {

        String okruh = (String) session.getAttribute("okruh");
        model.addAttribute("okruh", okruh);



        ArrayList<Slovo> slovicka = tabulka.fetchSlovos(okruh);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(slovicka);
        model.addAttribute("slovicka", json);

        return "nahled.html";
    }
}
