package symulacja.organizmy.rosliny;

import symulacja.organizmy.Organizm;
import symulacja.organizmy.Roslina;

import java.awt.*;

public class Trawa extends Roslina {

    public static final int SILA = 0;

    public Trawa(Wektor2d polozenie) {
        super(polozenie, SILA);
    }

    @Override
    public String toString(){

        return "trawa";

    }

    @Override
    public Color rysowanie() {

        return new Color(72,111,56);

    }

    @Override
    public Organizm kopia() {
        return new Trawa(polozenie);
    }

}
