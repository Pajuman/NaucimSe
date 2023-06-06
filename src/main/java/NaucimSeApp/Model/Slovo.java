package NaucimSeApp.Model;

public class Slovo {
    private final String cesky;
    private final String spanelsky;
    private int znalost;
    private final int key;

    public Slovo(int key, String cesky, String spanelsky, int znalost) {
        this.key = key;
        this.cesky = cesky;
        this.spanelsky = spanelsky;
        this.znalost = znalost;
    }

    public int getKey(){
        return key;
    }
    public String getCesky() {
        return cesky;}

    public String getSpanelsky() {
        return spanelsky;
    }

    public int getZnalost() {
        return znalost;
    }

    public void setZnalost(int znalost) {
        this.znalost = znalost;
    }

}
