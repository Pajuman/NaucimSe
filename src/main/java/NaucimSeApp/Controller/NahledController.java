package NaucimSeApp.Controller;

import NaucimSeApp.Model.Slovo;
import NaucimSeApp.Model.Tabulka;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/ukazat")
public class NahledController {
@Autowired
    Tabulka tabulka;

    @GetMapping("/{myVariable}")
    public String nactiMainUI(@PathVariable String myVariable, HttpSession session, Model model) throws JsonProcessingException {

        String okruh = (String) session.getAttribute("okruh");
        model.addAttribute("okruh", myVariable);
        model.addAttribute("akce", "pridat");

        ArrayList<Slovo> slovicka = tabulka.fetchSlovos(okruh);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(slovicka);
        model.addAttribute("slovicka", json);

        return "nahled.html";
    }

    @PostMapping("/pridaniSlova/{okruh}")
    public String noveSlovo(@PathVariable String okruh, @RequestParam("otazka") String otazka, @RequestParam("odpoved") String odpoved) {
        tabulka.pridejSlovo(okruh, otazka, odpoved);
        return "redirect:/ukazat/" + okruh;
    }

    @PostMapping("/zmenaSlova/{okruh}")
    public String zmenaSlova(@PathVariable String okruh, @RequestParam("idSlova") int id, @RequestParam("otazka") String otazka, @RequestParam("odpoved") String odpoved) {
        tabulka.zmenSlovo(id, okruh, otazka, odpoved);
        return "redirect:/ukazat/" + okruh;
    }

    @PostMapping("/smazaniSlova/{okruh}")
    public String smazaniSlova(@PathVariable String okruh, @RequestParam("idSlova") int id) {
        tabulka.smazSlovo(id, okruh);
        return "redirect:/ukazat/" + okruh;
    }
}
