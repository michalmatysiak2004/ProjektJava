package symulacja.organizmy.Zwierzeta;

import symulacja.organizmy.Organizm;

import java.awt.*;

public class Owca extends Zwierze {

    public static final int SILA = 4;
    public static final int INICJATYWA = 4;

    public Owca(Wektor2d polozenie) {
        super(polozenie, SILA, INICJATYWA);
    }

    @Override
    public String toString() {
        return "owca";
    }

    @Override
    public Color rysowanie() {
        return Color.GRAY;
    }

    @Override
    public Organizm kopia() {
        return new Owca(polozenie);
    }
}