package NaucimSeApp.Controller;

import NaucimSeApp.Model.Slovnik;
import NaucimSeApp.Model.Slovo;
import NaucimSeApp.Model.Tabulka;
import NaucimSeApp.Model.VytvorSlovnik;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class MainUIController {
    @Autowired
    Slovnik slovnik;
    @Autowired
    VytvorSlovnik vytvorSlovnik;
    @Autowired
    Tabulka tabulka;

    @GetMapping
    public String nactiMainUI(Model model) throws JsonProcessingException {

//        slovnik = vytvorSlovnik.vytvorSlovnik();
//        Slovo slovo = new Slovo(1,"aaa", "bbb", 5);

        ArrayList<String> okruhy = tabulka.prehledTabulek();


        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(okruhy);
        model.addAttribute("okruhy", json);

        return "mainUI.html";
    }

    @PostMapping("/submit")
    public String prijmiAkci(@RequestParam("okruh_selection") String okruh, @RequestParam("akce_selection") String akce, HttpSession session) {

        session.setAttribute("okruh", okruh);

        String resultPage;

        if(akce.equals("vyzkouset_akce")){
            resultPage = "redirect:/vyzkouset";
        } else {resultPage = "redirect:/ukazat/" + okruh;}

        return resultPage;

    }

    @PostMapping("/novyOkruh")
    public String novyOkruh(@RequestParam("novyOkruhNazev") String novyOkruhNazev, Model model) {

        tabulka.vytvorTabulku(novyOkruhNazev);
        model.addAttribute("okruh", novyOkruhNazev);

        return "redirect:/pridejslovo/" + novyOkruhNazev;

    }
}
