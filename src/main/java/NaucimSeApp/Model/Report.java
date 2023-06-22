package NaucimSeApp.Model;

import org.springframework.stereotype.Service;

//SQL dotazy v aplikaci zpravidla vrací Report se zprávou o průběhu dotazu
//atribut pozitivni nese informaci o úspěchu/neúspěchu provedeného dotazu a rozhoduje tak o barvě zprávy v prohlížeči (green/red)
@Service
public class Report {
    private String zprava;

    private Boolean pozitivni;

    public String getZprava() {
        return zprava;
    }

    public Boolean getPozitivni() {
        return pozitivni;
    }

    public void setZprava(String zprava) {
        this.zprava = zprava;
    }

    public void setPozitivni(Boolean pozitivni) {
        this.pozitivni = pozitivni;
    }
}
