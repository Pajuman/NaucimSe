package NaucimSeApp.Model;

public class Slovo {
    private final int key;
    private final String otazka;
    private final String odpoved;
    private int znalost;

    //Slovo zastupuje řádky v tabulkách databáze
    //metody níže jsou v tuto chvíli nadbytečné, poslouží ale při případném rozšíření aplikace
    public Slovo(int key, String otazka, String odpoved, int znalost) {
        this.key = key;
        this.otazka = otazka;
        this.odpoved = odpoved;
        this.znalost = znalost;
    }

    public int getKey(){
        return key;
    }

    public String getOtazka() {
        return otazka;}

    public String getOdpoved() {
        return odpoved;
    }

    public int getZnalost() {
        return znalost;
    }

    public void setZnalost(int znalost) {
        this.znalost = znalost;
    }

}
