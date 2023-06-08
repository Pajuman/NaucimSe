package NaucimSeApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pridejslovo")
public class PridejSlovo {

    @GetMapping("/{myVariable}")
    public String nactiPridejSlovicko(@PathVariable String myVariable, Model model){
        model.addAttribute("okruh", myVariable);
    return "pridejSlovo";
    }

    @PostMapping("/novyOkruh")
    public String novyOkrh(@RequestParam("novyOkruhNazev") String novyOkruhNazev) {
        System.out.println(novyOkruhNazev);
        return "mainUI";
    }
}
//    @RequestParam("otazka")String otazka, @RequestParam("odpoved")String odpoved