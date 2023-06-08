package NaucimSeApp.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VytvorSlovnik {
    @Autowired
    SlovaZDB slovaZDB;

    public Slovnik vytvorSlovnik(){

    Slovnik slovnik = new Slovnik();
    ArrayList<Slovo> prehledSlov = slovaZDB.fetchSlovos();
    slovnik.setPrehledSlov(prehledSlov);

    System.out.println("hohoooo");

    return slovnik;
    }
}
