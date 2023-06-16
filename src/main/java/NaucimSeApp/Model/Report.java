package NaucimSeApp.Model;

import org.springframework.stereotype.Service;

@Service
public class Report {
    private String Zprava;

    private Boolean Pozitivni;

    public String getZprava() {
        return Zprava;
    }

    public Boolean getPozitivni() {
        return Pozitivni;
    }

    public void setZprava(String zprava) {
        Zprava = zprava;
    }

    public void setPozitivni(Boolean pozitivni) {
        Pozitivni = pozitivni;
    }
}
