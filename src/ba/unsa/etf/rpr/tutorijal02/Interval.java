package ba.unsa.etf.rpr.tutorijal02;

import java.util.ArrayList;
import java.util.List;

public class Interval {

    private List<Tacka> tacke = new ArrayList<>();

    Interval(double prvaTacka, double drugaTacka, boolean prvaPripada, boolean drugaPripada) throws IllegalArgumentException {
        if (prvaTacka > drugaTacka) {
            throw new IllegalArgumentException();
        }
        tacke.add(new Tacka(prvaTacka, prvaPripada));
        tacke.add(new Tacka(drugaTacka, drugaPripada));
    }

    Interval() {
        tacke.add(new Tacka(0, false));
        tacke.add(new Tacka(0, false));
    }

    public boolean isNull() {
        return (tacke.get(0).getVrijednost() == 0 && !tacke.get(0).jeUkljucena() && tacke.get(1).getVrijednost() == 0 && !tacke.get(1).jeUkljucena());
    }

    public boolean isIn(double tacka) {
        return (tacka > tacke.get(0).getVrijednost() && tacka < tacke.get(1).getVrijednost()) || (tacke.get(0).getVrijednost() == tacka && tacke.get(0).jeUkljucena()) || (tacke.get(1).getVrijednost() == tacka && tacke.get(1).jeUkljucena());
    }

    public Interval intersect(Interval interval) throws IllegalArgumentException {
        Tacka tacka1 = this.tacke.get(0);
        Tacka tacka2 = this.tacke.get(1);
        return getInterval(interval, tacka1, tacka2);
    }

    public static Interval intersect(Interval interval1, Interval interval2) throws IllegalArgumentException {
        Tacka tacka1 = interval1.tacke.get(0);
        Tacka tacka2 = interval1.tacke.get(1);
        return getInterval(interval2, tacka1, tacka2);

    }

    @Override
    public String toString() {
        if(isNull()) return "()";

        String rezultat = "(";
        if (tacke.get(0).jeUkljucena()) rezultat = "[";
        rezultat += tacke.get(0).getVrijednost() + "," + tacke.get(1).getVrijednost();
        if (tacke.get(1).jeUkljucena()) rezultat += "]";
        else rezultat += ")";

        return rezultat;
    }

    @Override
    public boolean equals(Object o) {
        if(getClass() != o.getClass()) return false;

        Interval interval = (Interval) o;
        return (this.tacke.get(0).getVrijednost() == interval.tacke.get(0).getVrijednost() && this.tacke.get(0).jeUkljucena() == interval.tacke.get(0).jeUkljucena()
                && this.tacke.get(1).getVrijednost() == interval.tacke.get(1).getVrijednost() && this.tacke.get(1).jeUkljucena() == interval.tacke.get(1).jeUkljucena());
    }

    private static Interval getInterval(Interval interval2, Tacka tacka1, Tacka tacka2) throws IllegalArgumentException {
        if (interval2.tacke.get(0).getVrijednost() > tacka1.getVrijednost()) tacka1 = interval2.tacke.get(0);
        if (interval2.tacke.get(1).getVrijednost() < tacka2.getVrijednost()) tacka2 = interval2.tacke.get(1);
        return new Interval(tacka1.getVrijednost(), tacka2.getVrijednost(), tacka1.jeUkljucena(), tacka2.jeUkljucena());
    }
}