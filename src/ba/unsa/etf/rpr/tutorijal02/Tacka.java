package ba.unsa.etf.rpr.tutorijal02;

public class Tacka {

    private double vrijednost;
    private boolean ukljucena;

    public Tacka(double vrijednost, boolean jeUkljucena) {
        this.vrijednost = vrijednost;
        this.ukljucena = jeUkljucena;
    }

    public double getVrijednost() {
        return vrijednost;
    }

    public boolean jeUkljucena() {
        return ukljucena;
    }
}