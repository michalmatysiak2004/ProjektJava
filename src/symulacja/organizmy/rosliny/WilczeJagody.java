package symulacja.organizmy.rosliny;

import symulacja.organizmy.Organizm;
import symulacja.organizmy.Roslina;
import inne.*;
import java.awt.*;

public class WilczeJagody extends Roslina {
    public static int SILA = 0;


    public WilczeJagody(Wektor2d polozenie) {
        super(polozenie, SILA);
    }

    @Override
    public void dodajModyfikator(Organizm org){

        org.zabij();

    }

    @Override
    public String toString() {
        return "wilcze_jagody";
    }

    @Override
    public Color rysowanie() {
        return new Color(49, 0, 76);
    }

    @Override
    public WilczeJagody kopia() {
        return new WilczeJagody(polozenie);
    }
}