package NaucimSeApp.Controller;

import NaucimSeApp.Model.Slovo;
import NaucimSeApp.Model.Tabulka;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

//slouží k vyzkoušení ze slovíček a zobrazení úspěšnosti

@Controller
@RequestMapping("/vyzkouset")
public class VyzkousetController {

    @Autowired
    Tabulka tabulka;

    //"slovicka" jsou poslány do HTML jako JSON a odtud dále do JS pro práci s DOM (zkoušení a zobrazení výsledku)
    //model předá HTML název okruhu (nadpis)
    @GetMapping("/{okruh}")
    public String nactiVyyzkouset(@PathVariable String okruh, Model model) throws JsonProcessingException {

        ArrayList<Slovo> slovicka = tabulka.fetchSlovos(okruh);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(slovicka);


        model.addAttribute("slovicka", json);
        model.addAttribute("okruh", okruh);

        return "zkouseni.html";
    }
}