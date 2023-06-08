package NaucimSeApp.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
@Service
public class Slovnik {

    private ArrayList<Slovo> prehledSlov;

    public ArrayList<Slovo> getPrehledSlov() {
        return prehledSlov;
    }

    public void setPrehledSlov(ArrayList<Slovo> prehledSlov) {
        this.prehledSlov = prehledSlov;
    }

    public void seraditSlova (){
        prehledSlov.sort(Comparator.comparing(Slovo::getZnalost));
    }

    public Slovo vyberSlovo(){
        int hraniceVyberu = prehledSlov.size()/2;
        int rand =(int)(Math.random() * hraniceVyberu);

        return prehledSlov.get(rand);
    }

    public void vyhodnotSlovo(Slovo sl, boolean vedel){
        int zmenaZnalosti = (vedel) ? 1 : -1;
        int indexSlova = prehledSlov.indexOf(sl);
        prehledSlov.get(indexSlova).setZnalost(sl.getZnalost() + zmenaZnalosti);
    }


}
