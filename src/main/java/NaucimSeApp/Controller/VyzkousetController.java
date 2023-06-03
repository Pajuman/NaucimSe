package NaucimSeApp.Controller;

import NaucimSeApp.Model.AkceDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/akce")
public class ZkouseniController {



    @GetMapping
    public String nactiMainUI(AkceDTO dto) {
        return "zkouseni.html";
    }


}