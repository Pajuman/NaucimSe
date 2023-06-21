package NaucimSeApp.Controller;

import NaucimSeApp.Model.Report;
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

//slouží k zobrazení a editaci slovíček v okruhu (okruh je vybraná tabulka v databázi)

@Controller
@RequestMapping("/zobrazit")
public class ZobrazitController {
@Autowired
    Tabulka tabulka;
@Autowired
    Report report;

    //"slovicka" jsou poslány do HTML jako JSON a odtud dále do JS pro práci s DOM (Slovo se přidají jako řádky do tabulky)
    //model předá HTML název okruhu (nadpis)
    @GetMapping("/{okruh}")
    public String nactiZobraz(@PathVariable String okruh, Model model) throws JsonProcessingException {

        ArrayList<Slovo> slovicka = tabulka.fetchSlovos(okruh);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(slovicka);

        model.addAttribute("slovicka", json);
        model.addAttribute("okruh", okruh);

        return "nahled.html";
    }

    //přidání nového slova do tabulky (otázka, odpověď)
    @PostMapping("/pridaniSlova/{okruh}")
    public String noveSlovo(@PathVariable String okruh, @RequestParam("otazka") String otazka, @RequestParam("odpoved") String odpoved, RedirectAttributes redirectAttributes) {

        report = tabulka.pridejSlovo(okruh, otazka, odpoved);
        if(report.getPozitivni()) {
            redirectAttributes.addFlashAttribute("pozitivni", report.getZprava());
        }
        else{
            redirectAttributes.addFlashAttribute("negativni", report.getZprava());
        }

        return "redirect:/zobrazit/" + okruh;
    }

    //úprava stávajícího slova v tabulce (stávající id, nová otázka, nová odpověď)
    @PostMapping("/zmenaSlova/{okruh}")
    public String zmenaSlova(@PathVariable String okruh, @RequestParam("idSlova") int id, @RequestParam("otazka") String otazka, @RequestParam("odpoved") String odpoved, RedirectAttributes redirectAttributes) {

        report = tabulka.zmenSlovo(id, okruh, otazka, odpoved);
        if(report.getPozitivni()) {
            redirectAttributes.addFlashAttribute("pozitivni", report.getZprava());
        }
        else{
            redirectAttributes.addFlashAttribute("negativni", report.getZprava());
        }

        return "redirect:/zobrazit/" + okruh;
    }

    //smázání stávajícího slova na základě stávající id (id nebude znovu využito)
    @PostMapping("/smazaniSlova/{okruh}")
    public String smazaniSlova(@PathVariable String okruh, @RequestParam("idSlova") int id, RedirectAttributes redirectAttributes) {

        report = tabulka.smazSlovo(id, okruh);
        System.out.println(report.getZprava() + report.getPozitivni());
        if(report.getPozitivni()) {
            redirectAttributes.addFlashAttribute("pozitivni", report.getZprava());
        }
        else{
            redirectAttributes.addFlashAttribute("negativni", report.getZprava());
        }

        return "redirect:/zobrazit/" + okruh;
    }
}
