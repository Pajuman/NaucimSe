package NaucimSeApp.Controller;

import NaucimSeApp.Model.Report;
import NaucimSeApp.Model.Slovnik;
import NaucimSeApp.Model.Slovo;
import NaucimSeApp.Model.Tabulka;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class MainUIController {

    @Autowired
    Tabulka tabulka;

    @Autowired
    Report report;

    //"okruhy" jsou seznam tabulek v databázi
    //"okruhy" jsou poslány do HTML jako JSON a odtud dále do JS pro práci s DOM (tabulky se přidají jako option do selectu)
    @GetMapping
    public String nactiMainUI(Model model) throws JsonProcessingException {

        ArrayList<String> okruhy = tabulka.prehledTabulek();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(okruhy);
        model.addAttribute("okruhy", json);

        return "mainUI.html";
    }

    //poté, co je vybrán okruh (tabulka v DB) a akce, se tlačítkem "spustit" provede akce (skrz redirect se aktivuje jiný Controller)
    //pro přenos informace "okruh" mezi Controllery se použil query v URL (akce + okruh)
    @PostMapping("/submit")
    public String prijmiAkci(@RequestParam("okruh_selection") String okruh, @RequestParam("akce_selection") String akce, RedirectAttributes redirectAttributes) {

        String resultPage;
        ArrayList<Slovo> slovicka = tabulka.fetchSlovos(okruh);

        if(akce.equals("ukazat_akce")){
            resultPage = "redirect:/zobrazit/" + okruh;
        }
        else if(slovicka.size() < 3){
            resultPage = "redirect:/";
            redirectAttributes.addFlashAttribute("negativni", "Okruh musí mít alespoň 3 otázky, abyses mohl nechat vyzkoušet.");
        }
        else{
            resultPage = "redirect:/vyzkouset/" + okruh;
        }

        return resultPage;

    }

    //vytvoří nový "okruh" (tabulku) v databázi a vypíše barevně zprávu o úspěchu nebo neúspěchu
    @PostMapping("/novyOkruh")
    public String novyOkruh(@RequestParam("novyOkruhNazev") String novyOkruhNazev, Model model)  throws JsonProcessingException {

        report = tabulka.vytvorTabulku(novyOkruhNazev);
        model.addAttribute("okruh", novyOkruhNazev);

        if(report.getPozitivni()) {
            model.addAttribute("pozitivni", report.getZprava());
        }
        else{
            model.addAttribute("negativni", report.getZprava());
        }

        ArrayList<String> okruhy = tabulka.prehledTabulek();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(okruhy);
        model.addAttribute("okruhy", json);

        return "mainUI.html";

    }
}
